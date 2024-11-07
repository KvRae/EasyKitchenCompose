package com.kvrae.easykitchen.presentation.logic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.remote.dto.CategoryResponse
import com.kvrae.easykitchen.data.repository.CategoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: CategoryRepository,
) : ViewModel() {
    private val _categories = MutableStateFlow<List<CategoryResponse>>(emptyList())
    val categories = _categories
    var isLoading by mutableStateOf(false)


    init {
        fetchCategories()
    }

    fun fetchCategories() {
        viewModelScope.launch {
            isLoading = true
            delay(8000)
            val fetchedCategories = repository.getCategories()
            _categories.value = fetchedCategories
            isLoading = false
        }
    }
}
