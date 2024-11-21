package com.dom.data.mapper

import com.dom.data.model.Entities
import com.dom.data.model.ResponseData
import com.dom.domain.model.Data

object WeatherDataMapper {

    fun toData(res: ResponseData): Data {
        return when (res) {
            is ResponseData.WeatherRes -> {
                toWeatherInfo(res)
            }

            else -> Data.NoData
        }
    }

    fun toWeatherInfo(data: ResponseData.WeatherRes): Data.WeatherInfo {
        return Data.WeatherInfo(
            System.currentTimeMillis(),
            data.main.temperature,
            data.main.maxTemp,
            data.main.minTemp,
            data.main.humid,
            data.weathers.first().weatherGroup,
            data.weathers.first().weatherIcon
        )
    }

    fun toCityLog(data: Entities.LogCityName): Data.CityLog = Data.CityLog(data.name, data.timestamp)
    fun toLogCityName(data: Data.CityLog): Entities.LogCityName {
        val result = Entities.LogCityName(data.name)
        result.timestamp = data.timestamp
        return result
    }
}