package com.kvrae.easykitchen.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class User {
    @SerialName("_id")
    val id: String? = null
    @SerialName("username")
    val username: String? = null
    @SerialName("email")
    val email: String? = null
    @SerialName("password")
    val password: String? = null
}

data class LoginRequest(
    val username: String,
    val password: String
)

@Serializable
data class LoginResponse(
    @SerialName("user")
    val user: User,
    @SerialName("token")
    val token: String
)