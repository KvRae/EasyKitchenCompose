package com.kvrae.easykitchen.data.repository

import android.util.Log
import com.kvrae.easykitchen.data.client.KtorApiClient
import com.kvrae.easykitchen.data.models.Meal

class MealRepository(
    private val ktorApiClient: KtorApiClient
){

    suspend fun getMeals() : List<Meal> {
        return try {
            ktorApiClient.getMeals()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("MealRepository", "Error: ${e.message}")
            emptyList()
        }
    }
}
