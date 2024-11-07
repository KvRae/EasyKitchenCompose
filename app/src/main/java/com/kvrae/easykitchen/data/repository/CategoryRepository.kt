package com.kvrae.easykitchen.data.repository

import android.util.Log
import com.kvrae.easykitchen.data.remote.dto.CategoryResponse
import com.kvrae.easykitchen.network.client.KtorApiClient

class CategoryRepository(
    private val ktorApiClient: KtorApiClient,
) {
    suspend fun getCategories(): List<CategoryResponse> =
        try {
            ktorApiClient.getCategories()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("CategoryRepository", "Error: ${e.message}")
            emptyList()
        }
}
