package com.kvrae.easykitchen.presentation.logic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.remote.dto.Ingredient
import com.kvrae.easykitchen.data.remote.dto.IngredientResponse
import com.kvrae.easykitchen.data.remote.dto.MealDetail
import com.kvrae.easykitchen.data.repository.IngredientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val repository: IngredientRepository,
) : ViewModel() {
    private val _ingredients = MutableStateFlow<List<IngredientResponse>>(emptyList())
    val ingredients: StateFlow<List<IngredientResponse>> = _ingredients
    var isLoading by mutableStateOf(false)

    val ingredientsBasket = mutableStateListOf<Ingredient>()
    val mealsFound = mutableStateListOf<MealDetail>()


    init {
        fetchIngredients()
    }

    fun fetchIngredients() {
        viewModelScope.launch {
            isLoading = true
            val fetchedIngredients = repository.getIngredients()
            _ingredients.emit(fetchedIngredients)
            isLoading = false
        }
    }
}
