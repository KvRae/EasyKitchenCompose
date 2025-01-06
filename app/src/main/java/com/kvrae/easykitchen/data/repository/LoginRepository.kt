package com.kvrae.easykitchen.data.repository

import com.kvrae.easykitchen.data.remote.dto.User
import com.kvrae.easykitchen.network.client.KtorApiClient

class LoginRepository(
    private val ktorApiClient: KtorApiClient,
    ) {
    suspend fun login(username: String, password: String): User {
        return ktorApiClient.login(username, password)
    }


}