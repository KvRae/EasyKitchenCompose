package com.kvrae.easykitchen.data.models.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    @SerialName("_id")
    val idResponse: String?,
    @SerialName("strCategory")
    val strCategory: String?,
    @SerialName("strCategoryDescription")
    val strCategoryDescription: String?,
    @SerialName("strCategoryThumb")
    val strCategoryThumb: String?,
)
