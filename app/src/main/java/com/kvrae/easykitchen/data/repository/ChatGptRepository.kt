package com.kvrae.easykitchen.data.repository

import com.kvrae.easykitchen.data.remote.datasource.ChatGptRemoteDataSource
import com.kvrae.easykitchen.data.remote.dto.ChatRequest
import com.kvrae.easykitchen.data.remote.dto.ChatResponse

interface ChatGptRepository {
    suspend fun sendMessage(message: ChatRequest): Result<ChatResponse>
}

class ChatGptRepositoryImpl(private val remoteDataSource: ChatGptRemoteDataSource) : ChatGptRepository {
    override suspend fun sendMessage(request: ChatRequest): Result<ChatResponse> {
        return try {
            Result.success(remoteDataSource.sendMessage(request))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}