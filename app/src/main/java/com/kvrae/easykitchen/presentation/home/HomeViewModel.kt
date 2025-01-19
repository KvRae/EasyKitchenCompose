package com.kvrae.easykitchen.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.remote.dto.CategoryResponse
import com.kvrae.easykitchen.data.remote.dto.MealResponse
import com.kvrae.easykitchen.domain.usecases.GetCategoryUseCase
import com.kvrae.easykitchen.domain.usecases.GetMealsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mealsUseCase: GetMealsUseCase,
    private val categoryUseCase: GetCategoryUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Loading)
    val homeState: StateFlow<HomeState> = _homeState

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _homeState.value = HomeState.Loading

            val categoryResult = async { categoryUseCase() }.await()
            val mealResult = async { mealsUseCase() }.await()
            _homeState.value = when {
                categoryResult.isSuccess && mealResult.isSuccess -> {
                    val categories = categoryResult.getOrNull() ?: emptyList()
                    val meals = mealResult.getOrNull() ?: emptyList()
                    HomeState.Success(meals, categories)
                }
                categoryResult.isFailure || mealResult.isFailure -> {
                    HomeState.Error( "Failed to load Data")
                }
                else -> {HomeState.Error("Contents Not Available!")}
            }
        }
    }
}

sealed class HomeState{
    data object Loading : HomeState()
    data class Success(
        val meals: List<MealResponse>,
        val categories: List<CategoryResponse>
    ) : HomeState()
    data class Error(val message: String) : HomeState()
}