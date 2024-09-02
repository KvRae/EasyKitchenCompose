package com.kvrae.easykitchen.data.client

import android.util.Log
import com.kvrae.easykitchen.data.models.remote.CategoryResponse
import com.kvrae.easykitchen.data.models.remote.IngredientResponse
import com.kvrae.easykitchen.data.models.remote.MealResponse
import com.kvrae.easykitchen.utils.CATEGORIES_URL
import com.kvrae.easykitchen.utils.INGREDIENTS_URL
import com.kvrae.easykitchen.utils.MEALS_URL
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json

/**
 * KtorApiClient class to make API calls using Ktor client library
 * @property httpClient
 * @constructor Create empty Ktor api client
 *
 */
class KtorApiClient {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    prettyPrint = true //Prints the JSON in a human readable format
                    isLenient = true //Makes the parser ignore unknown keys
                    ignoreUnknownKeys = true //Ignores unknown keys
                }
            )
        }
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ResponseObserver) {
            onResponse { response ->
                Log.i("HTTP status:", "${response.status.value}")
            }
        }
        install(DefaultRequest) {
            header("Content-Type", "application/json")
            header("Accept", "application/json")
        }

    }

    suspend fun getMeals(): List<MealResponse> {
        val url = URLBuilder().apply {
            takeFrom(MEALS_URL)
        }
        return httpClient.get<List<MealResponse>>(url.build())
    }

    suspend fun getIngredients(): List<IngredientResponse> {
        val url = URLBuilder().apply {
            takeFrom(INGREDIENTS_URL)
        }
        return httpClient.get<List<IngredientResponse>>(url.build())
    }

    suspend fun getCategories(): List<CategoryResponse> {
        val url = URLBuilder().apply {
            takeFrom(CATEGORIES_URL)
        }
        return httpClient.get<List<CategoryResponse>>(url.build())
    }
}