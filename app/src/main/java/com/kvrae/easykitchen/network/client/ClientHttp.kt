package com.kvrae.easykitchen.network.client

import com.kvrae.easykitchen.data.remote.dto.CategoryResponse
import com.kvrae.easykitchen.data.remote.dto.IngredientResponse
import com.kvrae.easykitchen.data.remote.dto.MealResponse
import com.kvrae.easykitchen.data.remote.dto.User
import com.kvrae.easykitchen.utils.CATEGORIES_URL
import com.kvrae.easykitchen.utils.INGREDIENTS_URL
import com.kvrae.easykitchen.utils.LOGIN_URL
import com.kvrae.easykitchen.utils.MEALS_URL
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom

/**
 * KtorApiClient class to make API calls using Ktor client library
 * @property httpClient
 * @constructor Create empty Ktor api client
 *
 */
class KtorApiClient(
    private val httpClient: HttpClient
) {

    suspend fun getMeals(): List<MealResponse> {
        val url =
            URLBuilder().apply {
                takeFrom(MEALS_URL)
            }
        return httpClient.get<List<MealResponse>>(url.build())
    }

    suspend fun getIngredients(): List<IngredientResponse> {
        val url =
            URLBuilder().apply {
                takeFrom(INGREDIENTS_URL)
            }
        return httpClient.get<List<IngredientResponse>>(url.build())
    }

    suspend fun getCategories(): List<CategoryResponse> {
        val url =
            URLBuilder().apply {
                takeFrom(CATEGORIES_URL)
            }
        return httpClient.get<List<CategoryResponse>>(url.build())
    }

    suspend fun login(username: String, password: String): User {
        val url =
            URLBuilder().apply {
                takeFrom(LOGIN_URL)
                parameters.append("username", username)
                parameters.append("password", password)
            }
        return httpClient.post(url = url.build())
    }
}
