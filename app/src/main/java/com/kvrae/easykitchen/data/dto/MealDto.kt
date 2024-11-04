package com.kvrae.easykitchen.data.dto

import com.kvrae.easykitchen.data.models.remote.MealResponse

data class MealDto(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val category: String? = null,
    val area: String? = null,
    val image: String? = null,
)

data class MealDetailDto(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val category: String? = null,
    val area: String? = null,
    val image: String? = null,
    val instructions: String? = null,
    val tags: String? = null,
    val youtube: String? = null,
    val source: String? = null,
    val ingredients: List<String> = emptyList(),
    val measures: List<String> = emptyList(),
)

fun MealResponse.asDto(): MealDto =
    MealDto(
        id = idResponse,
        name = strMeal,
        category = strCategory,
        area = strArea,
        image = strMealThumb,
    )

fun MealResponse.asMealDetail(): MealDetailDto =
    MealDetailDto(
        id = idResponse,
        name = strMeal,
        category = strCategory,
        area = strArea,
        image = strMealThumb,
        instructions = strInstructions,
        youtube = strYoutube,
        source = strSource,
        ingredients =
            listOf(
                strIngredient1.orEmpty(),
                strIngredient2.orEmpty(),
                strIngredient3.orEmpty(),
                strIngredient4.orEmpty(),
                strIngredient5.orEmpty(),
                strIngredient6.orEmpty(),
                strIngredient7.orEmpty(),
                strIngredient8.orEmpty(),
                strIngredient9.orEmpty(),
                strIngredient10.orEmpty(),
                strIngredient11.orEmpty(),
                strIngredient12.orEmpty(),
                strIngredient13.orEmpty(),
                strIngredient14.orEmpty(),
                strIngredient15.orEmpty(),
                strIngredient16.orEmpty(),
                strIngredient17.orEmpty(),
                strIngredient18.orEmpty(),
                strIngredient19.orEmpty(),
                strIngredient20.orEmpty(),
            ).filter { it.isNotEmpty() },
        measures =
            listOf(
                strMeasure1.orEmpty(),
                strMeasure2.orEmpty(),
                strMeasure3.orEmpty(),
                strMeasure4.orEmpty(),
                strMeasure5.orEmpty(),
                strMeasure6.orEmpty(),
                strMeasure7.orEmpty(),
                strMeasure8.orEmpty(),
                strMeasure9.orEmpty(),
                strMeasure10.orEmpty(),
                strMeasure11.orEmpty(),
                strMeasure12.orEmpty(),
                strMeasure13.orEmpty(),
                strMeasure14.orEmpty(),
                strMeasure15.orEmpty(),
                strMeasure16.orEmpty(),
                strMeasure17.orEmpty(),
                strMeasure18.orEmpty(),
                strMeasure19.orEmpty(),
                strMeasure20.orEmpty(),
            ).filter { it.isNotEmpty() },
    )
