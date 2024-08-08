package com.example.singleactivityarchitecture.Network

import com.example.singleactivityarchitecture.dataBase.table.Poster
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val apiService: ApiService) {
    fun getPosterData(): Flow<DataResult<List<Poster>>> = flow {
        try {
            val response = apiService.fetchDisneyPosterList()
            emit(DataResult.Success(response))
        } catch (e: Exception) {
            emit(DataResult.Error(e))
        }
    }
}