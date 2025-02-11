package com.kvrae.easykitchen.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChatRequest(
    val model: String = "gpt-3.5-turbo",
    val message : String = "",
    val messages: List<Message> = emptyList()
)

@Serializable
data class Message(val role: String, val content: String)

@Serializable
data class ChatResponse(val choices: List<Choice>)

@Serializable
data class Choice(val message: Message)