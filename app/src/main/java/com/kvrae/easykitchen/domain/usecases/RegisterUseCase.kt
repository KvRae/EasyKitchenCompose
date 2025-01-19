package com.kvrae.easykitchen.domain.usecases

import com.kvrae.easykitchen.data.remote.dto.RegisterRequest
import com.kvrae.easykitchen.data.remote.dto.RegisterResponse
import com.kvrae.easykitchen.data.repository.RegisterRepository

class RegisterUseCase(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(username: String, email: String, password: String): Result<RegisterResponse> {
        return repository.register(
            RegisterRequest(
                username = username,
                email = email,
                password = password
            )
        )
    }
}