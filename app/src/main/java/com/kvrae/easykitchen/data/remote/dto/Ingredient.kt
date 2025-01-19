package com.kvrae.easykitchen.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientResponse(
    @SerialName("_id")
    val idResponse: String?,
    @SerialName("strDescription")
    val strDescription: String?,
    @SerialName("strIngredient")
    val strIngredient: String?,
)

data class Ingredient(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
)

fun IngredientResponse.asDto() =
    Ingredient(
        id = idResponse,
        name = strIngredient,
        description = strDescription
    )

