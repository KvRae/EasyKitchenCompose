package com.kvrae.easykitchen.data.repository

import com.kvrae.easykitchen.data.remote.datasource.MealRemoteDataSource
import com.kvrae.easykitchen.data.remote.dto.MealResponse

interface MealRepository {
    suspend fun getMeals() : Result<List<MealResponse>>
}

class MealRepositoryImpl(private val remoteDataSource: MealRemoteDataSource) : MealRepository {
    override suspend fun getMeals(): Result<List<MealResponse>> {
        return try {
            Result.success(remoteDataSource.getMeals())
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}
