package com.kvrae.easykitchen.presentation.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.remote.dto.ChatRequest
import com.kvrae.easykitchen.data.remote.dto.ChatResponse
import com.kvrae.easykitchen.domain.usecases.ChatGptUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatApi: ChatGptUseCase
) : ViewModel() {
    private val _chatState = MutableStateFlow<ChatState>(ChatState.Idle)
    val chatState: StateFlow<ChatState> = _chatState


    var userMessage by mutableStateOf("")

    val chatMessages = mutableStateListOf<Message>()


    fun sendMessage() {
        viewModelScope.launch {
            _chatState.value = ChatState.Idle
            if (userMessage.isBlank()) {
                _chatState.value = ChatState.Error("Message cannot be empty")
                return@launch
            }
            _chatState.value = ChatState.Loading
            chatMessages.add(Message(Role.USER, userMessage))
            val result = chatApi(ChatRequest(message = userMessage))
            _chatState.value = when {
                result.isSuccess -> {

                    chatMessages.add(Message(Role.ASSISTANT,result.getOrNull()!!.choices[0].message.content))
                    ChatState.Success(result.getOrNull()!!)

                }
                result.isFailure -> {
                    chatMessages.add(
                        Message(Role.ASSISTANT, "Failed to load data"))
                    ChatState.Error(result.exceptionOrNull()?.message ?: "Failed to load data")
                }

                else -> {
                    chatMessages.add(Message(Role.ASSISTANT,"Content is not available"))
                    ChatState.Error("Content is not available")

                }
            }
        }
    }
}

sealed class ChatState {
    data object Idle : ChatState()
    data object Loading : ChatState()
    data class Success(val response: ChatResponse) : ChatState()
    data class Error(val message: String) : ChatState()
}

data class Message(
    val role: Role,
    val content: String
)

enum class Role {
    USER,
    ASSISTANT
}