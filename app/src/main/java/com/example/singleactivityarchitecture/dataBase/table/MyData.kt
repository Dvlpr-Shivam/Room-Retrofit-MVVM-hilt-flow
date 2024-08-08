package com.example.singleactivityarchitecture.dataBase.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_table")
data class MyData(
    @PrimaryKey val id: Int,
    val name: String
)