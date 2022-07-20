package com.dom.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dom.data.model.LogCityName

@Database(entities = [LogCityName::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityNamLogDao(): CityNameLogDao
}