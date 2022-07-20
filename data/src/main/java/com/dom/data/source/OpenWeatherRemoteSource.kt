package com.dom.data.source

import com.dom.data.mapper.WeatherDataMapper
import com.dom.data.remote.OpenWeatherService
import com.dom.domain.model.WeatherInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface OpenWeatherRemoteSource {
    suspend fun getCurrentWeatherByCity(city: String): WeatherInfo
}

class OpenWeatherRemoteSourceImpl @Inject constructor(
    private val openWeatherService: OpenWeatherService,
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO
) : OpenWeatherRemoteSource {

    override suspend fun getCurrentWeatherByCity(city: String): WeatherInfo = withContext(ioDispatcher) {
        val response = openWeatherService.getByCityName(city)
        if (response.isSuccessful) {
            WeatherDataMapper.toWeatherInfo(response.body()!!)
        } else {
            throw Exception("Error!")
        }
    }
}