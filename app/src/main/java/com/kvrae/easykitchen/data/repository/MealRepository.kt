package com.kvrae.easykitchen.data.repository

import com.kvrae.easykitchen.data.remote.dto.MealResponse
import com.kvrae.easykitchen.network.client.KtorApiClient

class MealRepository(
    private val ktorApiClient: KtorApiClient,
) {
    suspend fun getMealsResponse(): List<MealResponse> =
        try {
            ktorApiClient.getMeals()
        } catch (e: Exception) {
            emptyList()
        }
    suspend fun getMeals(): List<MealResponse> {
        return emptyList()
    }
}
