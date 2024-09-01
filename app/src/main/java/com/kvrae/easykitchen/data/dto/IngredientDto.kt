package com.kvrae.easykitchen.data.dto

import com.kvrae.easykitchen.data.models.Ingredient

data class IngredientDto(
    val id: String? = null,
    val name: String? = null,
)

fun Ingredient.asDto() = IngredientDto(
    id = id?.oid,
    name = strIngredient,
)
