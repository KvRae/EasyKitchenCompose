package com.kvrae.easykitchen.data.repository

import android.util.Log
import com.kvrae.easykitchen.data.client.KtorApiClient
import com.kvrae.easykitchen.data.models.remote.IngredientResponse

class IngredientRepository(
    private val ktorApiClient: KtorApiClient
) {

        suspend fun getIngredients(): List<IngredientResponse> {
            return try {
                ktorApiClient.getIngredients()
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("IngredientRepository", "Error: ${e.message}")
                emptyList()
            }
        }
}