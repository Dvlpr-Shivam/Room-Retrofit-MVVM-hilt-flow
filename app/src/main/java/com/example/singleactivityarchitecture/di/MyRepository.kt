package com.example.singleactivityarchitecture.di

import com.example.singleactivityarchitecture.dataBase.MyDatabase
import com.example.singleactivityarchitecture.dataBase.dao.MyDao
import com.example.singleactivityarchitecture.dataBase.table.MyData
import com.example.singleactivityarchitecture.dataBase.table.Poster
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyRepository @Inject constructor(private val db: MyDatabase) {
    val allData: Flow<List<MyData>> = db.myDao().getAllData()
    val allPoster: Flow<List<Poster>> = db.posterDao().getPosterList()
    suspend fun insert(data: MyData) {
        db.myDao().insert(data)
    }

    suspend fun  insertPosterData(data :List<Poster>){
        db.posterDao().insertPosterList(data)
    }
    suspend fun getItemById(id:Long):Poster{
        return db.posterDao().getPoster(id)
    }
}