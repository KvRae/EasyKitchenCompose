package com.kvrae.easykitchen.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.kvrae.easykitchen.data.local.entity.SavedMeal

@Dao
interface SavedMealDao {
    @Query("SELECT * FROM saved_meals")
    suspend fun getAllSavedMeals(): List<SavedMeal>

    @Delete()
    suspend fun getSavedMealById(id: String): SavedMeal?

    @Query("DELETE FROM saved_meals")
    suspend fun deleteAllSavedMeals()

    @Query("DELETE FROM saved_meals WHERE id = :id")
    suspend fun deleteSavedMealById(id: String)

    @Upsert
    suspend fun saveMeal(savedMeal: SavedMeal)

}