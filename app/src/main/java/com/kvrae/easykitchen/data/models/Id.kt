package com.kvrae.easykitchen.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Id(
    @SerialName("\$oid")
    val oid: String? = null
)