package com.kvrae.easykitchen.presentation.login

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.kvrae.easykitchen.data.remote.dto.User
import com.kvrae.easykitchen.domain.usecases.GetGoogleSignInClientUseCase
import com.kvrae.easykitchen.domain.usecases.GetSignInIntentUseCase
import com.kvrae.easykitchen.domain.usecases.HandleSignInResultUseCase
import com.kvrae.easykitchen.domain.usecases.SendIdTokenToBackendUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GoogleAuthViewModel(
    private val getGoogleSignInClientUseCase: GetGoogleSignInClientUseCase,
    private val getSignInIntentUseCase: GetSignInIntentUseCase,
    private val handleSignInResultUseCase: HandleSignInResultUseCase,
    private val sendIdTokenToBackendUseCase: SendIdTokenToBackendUseCase

) : ViewModel() {
    private val _googleAuthState = MutableStateFlow<GoogleAuthState>(GoogleAuthState.Idle)
    val googleAuthState: StateFlow<GoogleAuthState> = _googleAuthState

    fun getGoogleSignInClient(activity: Activity): GoogleSignInClient {
        return getGoogleSignInClientUseCase(activity)
    }

    fun getSignInIntent(googleSignInClient: GoogleSignInClient): Intent {
        return getSignInIntentUseCase(googleSignInClient)
    }

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        viewModelScope.launch {
            _googleAuthState.value = GoogleAuthState.Loading
            val result = handleSignInResultUseCase(completedTask)
            _googleAuthState.value = when  {
                result.isSuccess -> {
                    val idToken = result.getOrThrow()
                    val backendResult = sendIdTokenToBackendUseCase(idToken)
                    when {
                        idToken.isEmpty() -> {
                            Log.d("GoogleAuthViewModel", "handleSignInResult: ID token is empty")
                            GoogleAuthState.Error("ID token is empty")
                        }
                        backendResult.isSuccess -> {
                            Log.d("GoogleAuthViewModel", "handleSignInResult: ${backendResult.getOrNull()}")
                            GoogleAuthState.Success(backendResult.getOrNull()?: User())
                        }
                        backendResult.isFailure -> {
                            Log.d("GoogleAuthViewModel", "handleSignInResult: ${backendResult
                                .exceptionOrNull()?.message}")
                            GoogleAuthState.Error(backendResult
                                .exceptionOrNull()?.message ?: "Backend authentication failed")
                        }
                        else -> {
                            Log.d("GoogleAuthViewModel", "handleSignInResult: Content is not available")
                            GoogleAuthState.Error("Content is not available")
                        }
                    }
                }
                result.isFailure -> {
                    Log.d("GoogleAuthViewModel", "handleSignInResult: ${result.exceptionOrNull()?.message}")
                    GoogleAuthState.Error(result
                    .exceptionOrNull()?.message ?: "Google sign in failed")

                }
                else -> {
                    Log.d("GoogleAuthViewModel", "handleSignInResult: Content is not available")
                    GoogleAuthState.Error("Content is not available")
                }
            }
        }
    }
    fun resetGoogleAuthState() {
        _googleAuthState.value = GoogleAuthState.Idle
    }
}

sealed class GoogleAuthState {
    data object Idle : GoogleAuthState()
    data object Loading : GoogleAuthState()
    data class Success(val user: User) : GoogleAuthState()
    data class Error(val message: String) : GoogleAuthState()
}