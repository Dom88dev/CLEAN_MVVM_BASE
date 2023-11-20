package com.dom.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dom.data.model.EntityLogCityName

@Database(entities = [EntityLogCityName::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityNamLogDao(): CityNameLogDao
}