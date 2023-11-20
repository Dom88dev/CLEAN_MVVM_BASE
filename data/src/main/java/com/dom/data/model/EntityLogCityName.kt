package com.dom.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_name_log")
data class EntityLogCityName(
    @PrimaryKey
    val name: String
) {
    var timestamp: Long = System.currentTimeMillis()
}
