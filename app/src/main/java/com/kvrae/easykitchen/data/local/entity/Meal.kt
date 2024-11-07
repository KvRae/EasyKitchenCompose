package com.kvrae.easykitchen.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val idTable : Int = 0,
    val idResponse: String = "",
    val name: String? = null,
    val source: String? = null,
    val category: String? = null,
    val area: String? = null,
    val image: String? = null,
    val instructions: String? = null,
    val youtube: String? = null,
    val ingredients: List<String> = emptyList(),
    val measures: List<String> = emptyList(),
    val isFavorite: Boolean = false
)