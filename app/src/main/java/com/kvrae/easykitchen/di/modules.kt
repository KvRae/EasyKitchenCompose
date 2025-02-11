package com.kvrae.easykitchen.di

import android.util.Log
import com.kvrae.easykitchen.data.local.database.EasyKitchenDb
import com.kvrae.easykitchen.data.remote.datasource.CategoryRemoteDataSource
import com.kvrae.easykitchen.data.remote.datasource.CategoryRemoteDataSourceImpl
import com.kvrae.easykitchen.data.remote.datasource.ChatGptDataSourceImpl
import com.kvrae.easykitchen.data.remote.datasource.ChatGptRemoteDataSource
import com.kvrae.easykitchen.data.remote.datasource.IngredientRemoteDataSource
import com.kvrae.easykitchen.data.remote.datasource.IngredientRemoteDataSourceImpl
import com.kvrae.easykitchen.data.remote.datasource.LoginRemoteDataSource
import com.kvrae.easykitchen.data.remote.datasource.LoginRemoteDataSourceImpl
import com.kvrae.easykitchen.data.remote.datasource.MealRemoteDataSource
import com.kvrae.easykitchen.data.remote.datasource.MealsRemoteDataSourceImpl
import com.kvrae.easykitchen.data.remote.datasource.RegisterRemoteDataSource
import com.kvrae.easykitchen.data.remote.datasource.RegisterRemoteDataSourceImpl
import com.kvrae.easykitchen.data.repository.AuthRepository
import com.kvrae.easykitchen.data.repository.AuthRepositoryImpl
import com.kvrae.easykitchen.data.repository.CategoryRepository
import com.kvrae.easykitchen.data.repository.CategoryRepositoryImpl
import com.kvrae.easykitchen.data.repository.ChatGptRepository
import com.kvrae.easykitchen.data.repository.ChatGptRepositoryImpl
import com.kvrae.easykitchen.data.repository.IngredientRepository
import com.kvrae.easykitchen.data.repository.IngredientRepositoryImpl
import com.kvrae.easykitchen.data.repository.LoginRepository
import com.kvrae.easykitchen.data.repository.LoginRepositoryImpl
import com.kvrae.easykitchen.data.repository.MealRepository
import com.kvrae.easykitchen.data.repository.MealRepositoryImpl
import com.kvrae.easykitchen.data.repository.RegisterRepository
import com.kvrae.easykitchen.data.repository.RegisterRepositoryImpl
import com.kvrae.easykitchen.domain.usecases.ChatGptUseCase
import com.kvrae.easykitchen.domain.usecases.GetCategoryUseCase
import com.kvrae.easykitchen.domain.usecases.GetGoogleSignInClientUseCase
import com.kvrae.easykitchen.domain.usecases.GetIngredientsUseCase
import com.kvrae.easykitchen.domain.usecases.GetMealsUseCase
import com.kvrae.easykitchen.domain.usecases.GetSignInIntentUseCase
import com.kvrae.easykitchen.domain.usecases.HandleSignInResultUseCase
import com.kvrae.easykitchen.domain.usecases.LoginUseCase
import com.kvrae.easykitchen.domain.usecases.RegisterUseCase
import com.kvrae.easykitchen.domain.usecases.SendIdTokenToBackendUseCase
import com.kvrae.easykitchen.presentation.chat.ChatViewModel
import com.kvrae.easykitchen.presentation.home.HomeViewModel
import com.kvrae.easykitchen.presentation.ingrendient.IngredientViewModel
import com.kvrae.easykitchen.presentation.login.GoogleAuthViewModel
import com.kvrae.easykitchen.presentation.login.LoginViewModel
import com.kvrae.easykitchen.presentation.meals.MealsViewModel
import com.kvrae.easykitchen.presentation.register.RegisterViewModel
import com.kvrae.easykitchen.utils.UserPreferencesManager
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
                connectTimeoutMillis = 10000
                socketTimeoutMillis = 10000
            }
            engine {
                connectTimeout = 100_000
            }
        }
    }
}

val dataModule = module {

    single<LoginRemoteDataSource> { LoginRemoteDataSourceImpl(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }

    single<RegisterRemoteDataSource> { RegisterRemoteDataSourceImpl(get()) }
    single<RegisterRepository> { RegisterRepositoryImpl(get()) }

    single<IngredientRemoteDataSource> { IngredientRemoteDataSourceImpl(get()) }
    single<IngredientRepository> { IngredientRepositoryImpl(get()) }

    single<CategoryRemoteDataSource> {CategoryRemoteDataSourceImpl(get())}
    single<CategoryRepository> { CategoryRepositoryImpl(get())  }

    single<MealRemoteDataSource> {MealsRemoteDataSourceImpl(get())}
    single<MealRepository>{MealRepositoryImpl(get())}

    single<AuthRepository> { AuthRepositoryImpl(get()) }

    single<ChatGptRemoteDataSource> { ChatGptDataSourceImpl(get()) }
    single<ChatGptRepository> { ChatGptRepositoryImpl(get()) }

    single { UserPreferencesManager(get()) }
}

val domainModule = module {
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { GetIngredientsUseCase(get()) }
    factory { GetCategoryUseCase(get()) }
    factory { GetMealsUseCase(get()) }
    factory { GetGoogleSignInClientUseCase(get()) }
    factory { GetSignInIntentUseCase(get()) }
    factory { HandleSignInResultUseCase(get()) }
    factory { SendIdTokenToBackendUseCase(get()) }
    factory { ChatGptUseCase(get()) }
}

val presentationModule = module {
    viewModel { MealsViewModel(get()) }
    viewModel { IngredientViewModel(get()) }
    viewModel { LoginViewModel(
        get<LoginUseCase>(),
        get<UserPreferencesManager>()
    ) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get<GetMealsUseCase>(), get<GetCategoryUseCase>()) }
    viewModel {
        GoogleAuthViewModel(
            get<GetGoogleSignInClientUseCase>(),
            get<GetSignInIntentUseCase>(),
            get<HandleSignInResultUseCase>(),
            get<SendIdTokenToBackendUseCase>()
        )
    }
    viewModel { ChatViewModel(get()) }
}

val databaseModule = module {
    single { EasyKitchenDb.getInstance(get()) }
}