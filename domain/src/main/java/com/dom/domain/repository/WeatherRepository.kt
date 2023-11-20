package com.dom.domain.repository

import com.dom.domain.model.Data.CityLog
import com.dom.domain.model.Data.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherByCity(cityName: String) : WeatherInfo
    suspend fun getCityNameLogs() : Flow<List<CityLog>>
    suspend fun insertLog(log: CityLog)
}