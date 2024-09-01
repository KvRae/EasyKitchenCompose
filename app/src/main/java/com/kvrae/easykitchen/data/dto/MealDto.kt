package com.kvrae.easykitchen.data.dto

import com.kvrae.easykitchen.data.models.Meal

data class MealDto(
    val id: String? = null,
    val name: String? = null,
    val description : String? = null,
    val category: String? = null,
    val area : String? = null,
    val image : String? = null
)

fun Meal.asDto(): MealDto {
    return MealDto(
        id = id?.oid,
        name = strMeal,
        category = strCategory,
        area = strArea,
        image = strMealThumb

    )
}

