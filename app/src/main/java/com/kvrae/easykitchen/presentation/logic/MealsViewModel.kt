package com.kvrae.easykitchen.presentation.logic
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.remote.dto.MealResponse
import com.kvrae.easykitchen.data.remote.dto.asDto
import com.kvrae.easykitchen.data.repository.MealRepository
import com.kvrae.easykitchen.utils.getMealCategoryByTime
import com.kvrae.easykitchen.utils.getMealTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealsViewModel(
    private val repository: MealRepository,
) : ViewModel() {
    private val _meals = MutableStateFlow<List<MealResponse>>(emptyList())
    val meals: StateFlow<List<MealResponse>> = _meals

    private val _mealsByTime = MutableStateFlow<List<MealResponse>>(emptyList())
    val mealsByTime: StateFlow<List<MealResponse>> = _mealsByTime

    val mealTime by mutableStateOf(getMealTime())

    var isLoading by mutableStateOf(false)

    init {
        fetchMeals()
    }

    fun fetchMeals() {
        viewModelScope.launch {
            isLoading = true
            val fetchedMeals = repository.getMealsResponse() // Fetch meals first
            _meals.emit(fetchedMeals)
            filterMealsByTime()
            isLoading = false
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