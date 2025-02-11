package com.kvrae.easykitchen.data.remote.datasource
import com.kvrae.easykitchen.data.remote.dto.ChatRequest
import com.kvrae.easykitchen.data.remote.dto.ChatResponse
import com.kvrae.easykitchen.data.remote.dto.Message
import com.kvrae.easykitchen.utils.CHAT_API_KEY
import com.kvrae.easykitchen.utils.CHAT_BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType


interface ChatGptRemoteDataSource {
    suspend fun sendMessage(request: ChatRequest): ChatResponse

}

class ChatGptDataSourceImpl(
    private val client: HttpClient
) : ChatGptRemoteDataSource {
        private val _apiKey = CHAT_API_KEY
    override suspend fun sendMessage(request: ChatRequest): ChatResponse {
        val requestBody = ChatRequest(
            model = "gpt-4o-mini",
            message = request.message,
            messages = listOf(Message("user",request.message))
        )

        return client.post(CHAT_BASE_URL) {
            header(HttpHeaders.Authorization, "Bearer $_apiKey")
            contentType(ContentType.Application.Json)
            body = requestBody
        }
    }
}
