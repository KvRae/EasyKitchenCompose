package com.kvrae.easykitchen.data.remote.datasource

import com.kvrae.easykitchen.data.remote.dto.LoginRequest
import com.kvrae.easykitchen.data.remote.dto.LoginResponse
import com.kvrae.easykitchen.utils.LOGIN_URL
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface LoginRemoteDataSource {
    suspend fun login(loginRequest: LoginRequest): LoginResponse

}

class LoginRemoteDataSourceImpl(private val client: HttpClient) : LoginRemoteDataSource {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return client.post(LOGIN_URL) {
            contentType(ContentType.Application.Json)
            body = loginRequest
        }
    }

}