package com.dom.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dom.data.model.EntityLogCityName
import kotlinx.coroutines.flow.Flow

@Dao
interface CityNameLogDao {

    @Query("SELECT * FROM city_name_log ORDER BY timestamp DESC")
    fun getAll(): Flow<List<EntityLogCityName>>

    @Insert
    fun insertAll(vararg logs: EntityLogCityName)

    @Query("DELETE FROM city_name_log")
    fun nukeTable()
}