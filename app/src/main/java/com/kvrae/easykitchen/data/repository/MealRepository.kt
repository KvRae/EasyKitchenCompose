package com.kvrae.easykitchen.data.repository

import android.util.Log
import com.kvrae.easykitchen.data.models.remote.MealResponse
import com.kvrae.easykitchen.data.remote.client.KtorApiClient

class MealRepository(
    private val ktorApiClient: KtorApiClient,
) {
    suspend fun getMeals(): List<MealResponse> =
        try {
            ktorApiClient.getMeals()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("MealRepository", "Error: ${e.message}")
            emptyList()
        }
}
