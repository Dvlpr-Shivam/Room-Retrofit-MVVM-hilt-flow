package com.example.singleactivityarchitecture.di

import android.content.Context
import com.example.singleactivityarchitecture.dataBase.MyDatabase
import com.example.singleactivityarchitecture.dataBase.dao.MyDao
import com.example.singleactivityarchitecture.dataBase.dao.PosterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MyDatabase {
        return MyDatabase.getDatabase(appContext)
    }

    @Provides
    fun provideMyDao(db: MyDatabase): MyDao {
        return db.myDao()
    }
    @Provides
    fun providePosterDao(db: MyDatabase): PosterDao {
        return db.posterDao()
    }

    @Provides
    fun provideRepository(myDao: MyDatabase): MyRepository {
        return MyRepository(myDao)
    }

}