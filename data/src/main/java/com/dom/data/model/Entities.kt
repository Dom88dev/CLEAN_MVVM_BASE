package com.dom.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class Entities {
    @Entity(tableName = "city_name_log")
    data class LogCityName(
        @PrimaryKey
        val name: String
    ): Entities() {
        var timestamp: Long = System.currentTimeMillis()
    }
}