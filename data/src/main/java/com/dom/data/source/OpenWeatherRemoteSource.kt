package com.dom.data.source

import com.dom.data.mapper.WeatherDataMapper
import com.dom.data.remote.OpenWeatherService
import com.dom.domain.model.Data.WeatherInfo
import javax.inject.Inject

interface OpenWeatherRemoteSource {
    suspend fun getCurrentWeatherByCity(city: String): WeatherInfo
}

class OpenWeatherRemoteSourceImpl @Inject constructor(
    private val openWeatherService: OpenWeatherService
) : OpenWeatherRemoteSource {

    override suspend fun getCurrentWeatherByCity(city: String): WeatherInfo {
        return WeatherDataMapper.toData(openWeatherService.getByCityName(city)) as WeatherInfo
    }
}