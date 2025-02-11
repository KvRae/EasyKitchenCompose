package com.kvrae.easykitchen.domain.usecases

import com.kvrae.easykitchen.data.remote.dto.ChatRequest
import com.kvrae.easykitchen.data.remote.dto.ChatResponse
import com.kvrae.easykitchen.data.repository.ChatGptRepository


class ChatGptUseCase(
    private val repository: ChatGptRepository
) {
    suspend operator fun invoke(request: ChatRequest ): Result<ChatResponse> {
        return repository.sendMessage(request)
    }
}