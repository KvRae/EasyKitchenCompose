package com.kvrae.easykitchen.domain.usecases

import com.kvrae.easykitchen.data.remote.dto.IngredientResponse
import com.kvrae.easykitchen.data.repository.IngredientRepository

class GetIngredientsUseCase(
    private val repository: IngredientRepository
) {
   suspend operator fun invoke(): Result<List<IngredientResponse>> {
        return repository.getIngredients()
   }
}