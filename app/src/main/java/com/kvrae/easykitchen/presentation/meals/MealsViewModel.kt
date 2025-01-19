package com.kvrae.easykitchen.presentation.meals
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.remote.dto.MealResponse
import com.kvrae.easykitchen.data.remote.dto.asDto
import com.kvrae.easykitchen.domain.usecases.GetMealsUseCase
import com.kvrae.easykitchen.utils.getMealCategoryByTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealsViewModel(
    private val getMealsUseCase: GetMealsUseCase,
) : ViewModel() {

    private val _mealState = MutableStateFlow<MealState>(MealState.Loading)
    val mealState : StateFlow<MealState> = _mealState

    private val _meals = MutableStateFlow<List<MealResponse>>(emptyList())
    val meals: StateFlow<List<MealResponse>> = _meals

    private val _mealsByTime = MutableStateFlow<List<MealResponse>>(emptyList())
    val mealsByTime: StateFlow<List<MealResponse>> = _mealsByTime

    init {
        fetchMeals()
    }

    fun fetchMeals() {
        viewModelScope.launch {
            _mealState.value = MealState.Loading
            val result = getMealsUseCase()
            _mealState.value = when{
                result.isSuccess -> {
                    _meals.value = result.getOrNull()!!
                    MealState.Success(result.getOrNull()!!)
                }
                result.isFailure -> {
                    MealState.Error(result.exceptionOrNull()?.message ?: "Failed to load data")
                }
                else -> {
                    MealState.Error("Unknown error")
                }
            }

        }
    }

    fun findMealById(mealId: String?): MealResponse? {
        return meals.value.find { it.idResponse == mealId }
    }

    private fun filterMealsByTime() {
        viewModelScope.launch {
            _mealsByTime.emit(meals.value.filter { it.asDto().category == getMealCategoryByTime() })
        }
    }
}

sealed class MealState {
    data object Loading: MealState()
    data class Success(val data : List<MealResponse>): MealState()
    data class Error(val message: String): MealState()
}