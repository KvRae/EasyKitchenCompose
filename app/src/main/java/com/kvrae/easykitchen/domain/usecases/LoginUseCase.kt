package com.kvrae.easykitchen.domain.usecases

import com.kvrae.easykitchen.data.remote.dto.LoginRequest
import com.kvrae.easykitchen.data.remote.dto.LoginResponse
import com.kvrae.easykitchen.data.repository.LoginRepository

class LoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(request: LoginRequest): Result<LoginResponse> {
        return repository.login(request)
    }
}