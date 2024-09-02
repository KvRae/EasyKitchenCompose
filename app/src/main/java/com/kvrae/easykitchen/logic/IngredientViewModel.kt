package com.kvrae.easykitchen.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.models.remote.IngredientResponse
import com.kvrae.easykitchen.data.repository.IngredientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IngredientViewModel(
    private val repository: IngredientRepository
) : ViewModel() {
    private val _ingredients = MutableStateFlow<List<IngredientResponse>>(emptyList())
    val ingredients : StateFlow<List<IngredientResponse>> = _ingredients

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