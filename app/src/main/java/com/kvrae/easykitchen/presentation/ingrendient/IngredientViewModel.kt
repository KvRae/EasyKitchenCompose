package com.kvrae.easykitchen.presentation.ingrendient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.remote.dto.IngredientResponse
import com.kvrae.easykitchen.domain.usecases.GetIngredientsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IngredientViewModel(private val getIngredientsUseCase: GetIngredientsUseCase) : ViewModel() {
    private val _ingredientsState = MutableStateFlow<IngredientState>(IngredientState.Loading)
    val ingredientsState: StateFlow<IngredientState> = _ingredientsState

    init {
        getIngredients()
    }

    fun getIngredients() {
        viewModelScope.launch {
            val result = getIngredientsUseCase()
            _ingredientsState.value = when {
                result.isSuccess -> {
                    IngredientState.Success(result.getOrNull()!!)
                }
                result.isFailure -> {
                    IngredientState.Error(result.exceptionOrNull()?.message ?: "Failed to load data")
                    }
                else -> {
                    IngredientState.Error("Unknown error")
            }
            }
        }
    }
}

sealed class IngredientState {
    data object Loading : IngredientState()
    data class Success(val ingredients: List<IngredientResponse>) : IngredientState()
    data class Error(val message: String) : IngredientState()
}
