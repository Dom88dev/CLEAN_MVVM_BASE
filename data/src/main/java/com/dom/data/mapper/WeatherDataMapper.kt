package com.dom.data.mapper

import com.dom.data.model.LogCityName
import com.dom.data.model.WeatherRes
import com.dom.domain.model.CityLog
import com.dom.domain.model.WeatherInfo

object WeatherDataMapper {

    fun toWeatherInfo(data: WeatherRes) : WeatherInfo {
        return WeatherInfo(
            System.currentTimeMillis(),
            data.main.temperature,
            data.main.maxTemp,
            data.main.minTemp,
            data.main.humid,
            data.weathers.first().weatherGroup,
            data.weathers.first().weatherIcon
        )
    }

    fun toCityLog(data: LogCityName) : CityLog = CityLog(data.name, data.timestamp)
    fun toLogCityName(data: CityLog) : LogCityName {
        val result = LogCityName(data.name)
        result.timestamp = data.timestamp
        return result
    }
}