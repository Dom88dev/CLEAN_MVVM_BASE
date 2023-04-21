package com.dom.clean_mvvm_base.di

import android.content.Context
import androidx.room.Room
import com.dom.data.local.AppDatabase
import com.dom.data.local.CityNameLogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, "logging.db"
    ).build()

    @Provides
    fun provideLogDao(database: AppDatabase): CityNameLogDao = database.cityNamLogDao()
}