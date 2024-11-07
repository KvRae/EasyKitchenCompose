package com.kvrae.easykitchen.di

import android.util.Log
import com.kvrae.easykitchen.data.local.database.EasyKitchenDb
import com.kvrae.easykitchen.data.repository.CategoryRepository
import com.kvrae.easykitchen.data.repository.IngredientRepository
import com.kvrae.easykitchen.data.repository.MealRepository
import com.kvrae.easykitchen.domain.usecases.LoginUseCase
import com.kvrae.easykitchen.network.client.KtorApiClient
import com.kvrae.easykitchen.presentation.logic.CategoryViewModel
import com.kvrae.easykitchen.presentation.logic.IngredientViewModel
import com.kvrae.easykitchen.presentation.logic.MealsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { KtorApiClient(get()) }
    single {
        HttpClient(Android) {
            install(JsonFeature) {
                serializer =
                    KotlinxSerializer(
                        Json {
                            prettyPrint = true // Prints the JSON in a human readable format
                            isLenient = true // Makes the parser ignore unknown keys
                            ignoreUnknownKeys = true // Ignores unknown keys
                        },
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
            install(HttpTimeout) {
                requestTimeoutMillis = 10000
            }
        }
    }
}

val dataModule = module {
    single { MealRepository(get()) }
    single { IngredientRepository(get()) }
    single { CategoryRepository(get()) }

}

val domainModule = module {
    factory { LoginUseCase(get()) }
}

val presentationModule = module {
    viewModel { MealsViewModel(get()) }
    viewModel { IngredientViewModel(get()) }
    viewModel { CategoryViewModel(get()) }
}

val databaseModule = module {
    single { EasyKitchenDb.getInstance(get()) }
}