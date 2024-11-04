package com.kvrae.easykitchen.data.models.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdResponse(
    @SerialName("_id")
    val id: String? = null,
)
