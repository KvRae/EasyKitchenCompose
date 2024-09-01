package com.kvrae.easykitchen.data.dto

import com.kvrae.easykitchen.data.models.Category

data class CategoryDto(
    val id: String? = null,
    val name: String? = null,
    val image : String? = null
)

fun Category.asDto(): CategoryDto {
    return CategoryDto(
        id = id?.oid,
        name = strCategory,
        image = strCategoryThumb
    )
}
