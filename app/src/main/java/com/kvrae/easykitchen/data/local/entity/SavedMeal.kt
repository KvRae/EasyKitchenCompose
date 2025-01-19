package com.kvrae.easykitchen.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kvrae.easykitchen.data.remote.dto.Meal

@Entity(tableName = "saved_meal")
data class SavedMeal(
    @PrimaryKey(autoGenerate = true)
    val idTable: Int = 0,
    val id : String? = null,
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

fun Meal.asSavedMeal() : SavedMeal {
    return SavedMeal(
        id = this.id,
        name = this.name,
        source = this.source,
        category = this.category,
        area = this.area,
        image = this.image,
        instructions = this.instructions,
        youtube = this.youtube,
        ingredients = this.ingredients,
        measures = this.measures
    )
}
