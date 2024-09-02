package com.kvrae.easykitchen.data.dto

import com.kvrae.easykitchen.data.models.remote.IngredientResponse

data class IngredientDto(
    val id: String? = null,
    val name: String? = null,
)

fun IngredientResponse.asDto() = IngredientDto(
    id = idResponse?.oid,
    name = strIngredient,
)
