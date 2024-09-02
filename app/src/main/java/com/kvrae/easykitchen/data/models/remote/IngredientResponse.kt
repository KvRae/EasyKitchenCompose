package com.kvrae.easykitchen.data.models.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientResponse(
    @SerialName("_id")
    val idResponse: IdResponse?,
    @SerialName("strDescription")
    val strDescription: String?,
    @SerialName("strIngredient")
    val strIngredient: String?
)