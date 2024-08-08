package com.example.singleactivityarchitecture.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.singleactivityarchitecture.dataBase.table.MyData
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {
    @Query("SELECT * FROM my_table")
    fun getAllData(): Flow<List<MyData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: MyData)
}