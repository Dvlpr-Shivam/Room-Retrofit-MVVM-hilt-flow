package com.example.singleactivityarchitecture.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.singleactivityarchitecture.dataBase.dao.MyDao
import com.example.singleactivityarchitecture.dataBase.dao.PosterDao
import com.example.singleactivityarchitecture.dataBase.table.MyData
import com.example.singleactivityarchitecture.dataBase.table.Poster

@Database(entities = [MyData::class,Poster::class], version = 2, exportSchema = false)
abstract class MyDatabase: RoomDatabase()  {
    abstract fun myDao(): MyDao
    abstract fun posterDao(): PosterDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}