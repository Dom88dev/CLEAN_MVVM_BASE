package com.dom.data.repository

import com.dom.data.source.LogDataSource
import com.dom.data.source.OpenWeatherRemoteSource
import com.dom.domain.model.Data.CityLog
import com.dom.domain.model.Data.WeatherInfo
import com.dom.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val openWeatherRemoteSource: OpenWeatherRemoteSource,
    private val logDataSource: LogDataSource,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : WeatherRepository {

    override suspend fun getWeatherByCity(cityName: String): WeatherInfo {
        return openWeatherRemoteSource.getCurrentWeatherByCity(cityName)
    }

    override suspend fun getCityNameLogs(): Flow<List<CityLog>> = withContext(defaultDispatcher) {
        logDataSource.getAllLogs()
    }

    override suspend fun insertLog(log: CityLog) = withContext(defaultDispatcher) {
        logDataSource.addLog(log)
    }
}