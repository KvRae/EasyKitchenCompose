package com.kvrae.easykitchen.data.repository

import com.kvrae.easykitchen.data.remote.datasource.RegisterRemoteDataSource
import com.kvrae.easykitchen.data.remote.dto.RegisterRequest
import com.kvrae.easykitchen.data.remote.dto.RegisterResponse
import com.kvrae.easykitchen.domain.handlers.RegisterFailureException
import io.ktor.network.sockets.SocketTimeoutException
import java.net.ConnectException

interface RegisterRepository {
    suspend fun register(request: RegisterRequest): Result<RegisterResponse>
}

class RegisterRepositoryImpl(private val remoteDataSource: RegisterRemoteDataSource): RegisterRepository {
    override suspend fun register(request: RegisterRequest): Result<RegisterResponse> {
        return try{
            Result.success(remoteDataSource.register(request))
        } catch (e: SocketTimeoutException) {
            Result.failure(RegisterFailureException("Connection timed out"))
        } catch (e: ConnectException) {
            Result.failure(RegisterFailureException("Unable to connect to server"))
        } catch (e: Exception) {
            Result.failure(RegisterFailureException("There was an error registering the user"))

        }
    }

}