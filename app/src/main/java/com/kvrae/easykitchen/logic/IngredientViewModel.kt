package com.kvrae.easykitchen.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.models.Ingredient
import com.kvrae.easykitchen.data.repository.IngredientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val repository: IngredientRepository
) : ViewModel() {
    private val _ingredients = MutableStateFlow<List<Ingredient>>(emptyList())
    val ingredients : StateFlow<List<Ingredient>> = _ingredients

    init {
        fetchIngredients()

    }

    private fun fetchIngredients() {
        viewModelScope.launch {
            val fetchedIngredients = repository.getIngredients()
            _ingredients.emit(fetchedIngredients)
        }
    }
}