package com.kvrae.easykitchen.presentation.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.domain.usecases.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
): ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: MutableStateFlow<RegisterState> = _registerState

    val username = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")

    fun register() {
        val fUsername = username.value
        val fEmail = email.value
        val fPassword = password.value

        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            val result = registerUseCase(fUsername, fEmail, fPassword)
            _registerState.value = when {
                result.isSuccess -> {
                    RegisterState.Success(result.getOrNull()?.message ?: "Unknown error")
                }
                result.isFailure -> {
                    RegisterState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
                    }
                else -> {
                    RegisterState.Error("Unknown error")
                }
            }
        }
    }

}

sealed class RegisterState {
    data object Idle : RegisterState()
    data object Loading : RegisterState()
    data class Success(val message: String) : RegisterState()
    data class Error(val message: String) : RegisterState()

}