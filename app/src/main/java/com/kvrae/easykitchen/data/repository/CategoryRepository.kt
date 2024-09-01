package com.kvrae.easykitchen.data.repository

import android.util.Log
import com.kvrae.easykitchen.data.client.KtorApiClient
import com.kvrae.easykitchen.data.models.Category

class CategoryRepository(
    private val ktorApiClient: KtorApiClient
) {

            suspend fun getCategories(): List<Category> {
                return try {
                    ktorApiClient.getCategories()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("CategoryRepository", "Error: ${e.message}")
                    emptyList()
                }
            }
}