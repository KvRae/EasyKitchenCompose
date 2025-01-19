package com.kvrae.easykitchen.data.repository

import com.kvrae.easykitchen.data.remote.datasource.LoginRemoteDataSource
import com.kvrae.easykitchen.data.remote.dto.LoginRequest
import com.kvrae.easykitchen.data.remote.dto.LoginResponse
import com.kvrae.easykitchen.domain.handlers.LoginFailureException
import java.net.ConnectException
import java.net.SocketTimeoutException

interface LoginRepository {
    suspend fun login(request: LoginRequest): Result<LoginResponse>
}

class LoginRepositoryImpl(private val remoteDataSource: LoginRemoteDataSource) : LoginRepository {
    override suspend fun login(request: LoginRequest): Result<LoginResponse> {
        return try {
            Result.success(remoteDataSource.login(request))
        } catch (e: SocketTimeoutException) {
            Result.failure(LoginFailureException("Connection timed out"))
        } catch (e: ConnectException) {
            Result.failure(LoginFailureException("Unable to connect to server"))
        } catch (e: Exception) {
            Result.failure(LoginFailureException("Error logging in: ${e.message}"))
        }
    }
}