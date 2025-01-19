package com.kvrae.easykitchen.domain.usecases

import com.kvrae.easykitchen.data.remote.dto.CategoryResponse
import com.kvrae.easykitchen.data.repository.CategoryRepository

class GetCategoryUseCase(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): Result<List<CategoryResponse>> {
        return repository.getCategories()
    }
}