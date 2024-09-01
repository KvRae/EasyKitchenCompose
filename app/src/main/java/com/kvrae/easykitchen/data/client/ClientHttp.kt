package com.kvrae.easykitchen.data.client

import android.util.Log
import com.kvrae.easykitchen.data.models.Category
import com.kvrae.easykitchen.data.models.Ingredient
import com.kvrae.easykitchen.data.models.Meal
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

class KtorApiClient {
    private val TIME_OUT = 5000
    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
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
        engine {

        }
    }

    suspend fun getMeals(): List<Meal> {
        val url = URLBuilder().apply {
            takeFrom(MEALS_URL)
        }
        return httpClient.get<List<Meal>>(url.build())
    }

    suspend fun getIngredients(): List<Ingredient> {
        val url = URLBuilder().apply {
            takeFrom(INGREDIENTS_URL)
        }
        return httpClient.get<List<Ingredient>>(url.build())
    }

    suspend fun getCategories(): List<Category> {
        val url = URLBuilder().apply {
            takeFrom(CATEGORIES_URL)
        }
        return httpClient.get<List<Category>>(url.build())
    }
}