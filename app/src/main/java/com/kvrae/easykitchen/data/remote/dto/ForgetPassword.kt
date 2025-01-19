package com.kvrae.easykitchen.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForgetPasswordRequest (
    val email: String,
)

data class ForgetPasswordResponse(
    val message: String,
)

data class VerifyOtpRequest(
    val email: String,
    val otp: String,
)

data class VerifyOtpResponse(
    val message: String,
)

data class ResetPasswordRequest(
    val email: String,
    val newPassword: String,
)

data class ResetPasswordResponse(
    val message: String,
)

