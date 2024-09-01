package com.kvrae.easykitchen.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvrae.easykitchen.data.models.Category
import com.kvrae.easykitchen.data.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: CategoryRepository
): ViewModel() {

    private val _categories = MutableStateFlow<List<Category>> (emptyList())
    val categories = _categories

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            val fetchedCategories = repository.getCategories()
            _categories.value = fetchedCategories
        }
    }
}