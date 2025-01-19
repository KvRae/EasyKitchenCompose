package com.kvrae.easykitchen.data.repository

import com.kvrae.easykitchen.data.remote.datasource.CategoryRemoteDataSource
import com.kvrae.easykitchen.data.remote.dto.CategoryResponse

interface CategoryRepository {
    suspend fun getCategories(): Result<List<CategoryResponse>>

}

class CategoryRepositoryImpl(private val remoteDataSource: CategoryRemoteDataSource) : CategoryRepository {
    override suspend fun getCategories(): Result<List<CategoryResponse>> {
        return try {
            Result.success(remoteDataSource.getCategories())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}