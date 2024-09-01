package com.kvrae.easykitchen.di

import com.kvrae.easykitchen.data.client.KtorApiClient
import com.kvrae.easykitchen.data.repository.CategoryRepository
import com.kvrae.easykitchen.data.repository.IngredientRepository
import com.kvrae.easykitchen.data.repository.MealRepository
import com.kvrae.easykitchen.logic.CategoryViewModel
import com.kvrae.easykitchen.logic.IngredientViewModel
import com.kvrae.easykitchen.logic.MealsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { KtorApiClient() }
    single { MealRepository(get()) }
    single { IngredientRepository(get()) }
    single { CategoryRepository(get()) }

    viewModel { MealsViewModel(get()) }
    viewModel { IngredientViewModel(get()) }
    viewModel { CategoryViewModel(get()) }
}

