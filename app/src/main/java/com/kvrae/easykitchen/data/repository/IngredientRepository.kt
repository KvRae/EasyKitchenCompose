package com.kvrae.easykitchen.data.repository

import android.util.Log
import com.kvrae.easykitchen.data.remote.dto.IngredientResponse
import com.kvrae.easykitchen.network.client.KtorApiClient

class IngredientRepository(
    private val ktorApiClient: KtorApiClient,
) {
    suspend fun getIngredients(): List<IngredientResponse> =
        try {
            ktorApiClient.getIngredients()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("IngredientRepository", "Error: ${e.message}")
            emptyList()
        }
}
