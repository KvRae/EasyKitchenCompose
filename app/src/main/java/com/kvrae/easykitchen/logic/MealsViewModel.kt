package com.kvrae.easykitchen.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.models.Meal
import com.kvrae.easykitchen.data.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealsViewModel(private val repository: MealRepository) : ViewModel() {
    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals : StateFlow<List<Meal>> = _meals

    init {
        fetchMeals()

    }

    private fun fetchMeals() {
        viewModelScope.launch {
            val fetchedMeals = repository.getMeals()
            _meals.emit(fetchedMeals)
        }
    }
}