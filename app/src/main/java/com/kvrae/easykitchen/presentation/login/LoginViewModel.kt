package com.kvrae.easykitchen.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.remote.dto.LoginRequest
import com.kvrae.easykitchen.data.remote.dto.LoginResponse
import com.kvrae.easykitchen.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    val userName = mutableStateOf("")
    val password = mutableStateOf("")

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            if (username.isBlank()) {
                _loginState.value = LoginState.Error("Username cannot be empty")

            }
            if (password.isBlank()) {
                _loginState.value = LoginState.Error("Password cannot be empty")

            }
            val result = loginUseCase(LoginRequest(username, password))
            _loginState.value = when {
                result.isSuccess -> {
                    LoginState.Success(result.getOrNull()!!)
                }
                result.isFailure -> {
                    LoginState.Error(result.exceptionOrNull()?.message ?: "Failed to load data")
                }
                else -> {
                    LoginState.Error("Content is not available")
                }
            }
        }
    }
}

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val response: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}