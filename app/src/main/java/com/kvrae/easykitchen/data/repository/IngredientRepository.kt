package com.kvrae.easykitchen.data.repository

import com.kvrae.easykitchen.data.remote.datasource.IngredientRemoteDataSource
import com.kvrae.easykitchen.data.remote.dto.IngredientResponse

interface IngredientRepository {
    suspend fun getIngredients(): Result<List<IngredientResponse>>

}

class IngredientRepositoryImpl(private val remoteDataSource: IngredientRemoteDataSource) : IngredientRepository {
    override suspend fun getIngredients() : Result<List<IngredientResponse>> {
        return try {
            Result.success(remoteDataSource.getIngredients())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
