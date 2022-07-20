package com.dom.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dom.data.model.LogCityName
import kotlinx.coroutines.flow.Flow

@Dao
interface CityNameLogDao {

    @Query("SELECT * FROM city_name_log ORDER BY timestamp DESC")
    fun getAll(): Flow<List<LogCityName>>

    @Insert
    fun insertAll(vararg logs: LogCityName)

    @Query("DELETE FROM city_name_log")
    fun nukeTable()
}