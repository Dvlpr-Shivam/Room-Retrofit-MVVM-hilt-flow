package com.example.singleactivityarchitecture.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.singleactivityarchitecture.dataBase.table.Poster
import kotlinx.coroutines.flow.Flow

@Dao
interface PosterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosterList(posters: List<Poster>)

    @Query("SELECT * FROM Poster WHERE id = :id_")
    suspend fun getPoster(id_: Long):Poster

    @Query("SELECT * FROM Poster")
    fun getPosterList(): Flow<List<Poster>>
}