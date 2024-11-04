package com.kvrae.easykitchen.data.dto

import com.kvrae.easykitchen.data.models.remote.CategoryResponse

data class CategoryDto(
    val id: String? = null,
    val name: String? = null,
    val image: String? = null,
)

fun CategoryResponse.asDto(): CategoryDto =
    CategoryDto(
        id = idResponse,
        name = strCategory,
        image = strCategoryThumb,
    )
