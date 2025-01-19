package com.kvrae.easykitchen.domain.usecases

import com.kvrae.easykitchen.data.remote.dto.MealResponse
import com.kvrae.easykitchen.data.repository.MealRepository

class GetMealsUseCase(
    private val repository: MealRepository
) {
  suspend operator fun invoke(): Result<List<MealResponse>>{
      return repository.getMeals()
  }
}