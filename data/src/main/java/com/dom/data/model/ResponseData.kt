package com.dom.data.model

import com.google.gson.annotations.SerializedName

sealed class ResponseData {
    data class WeatherRes(
        @SerializedName("coord") val coord: Coordinates,
        @SerializedName("weather") val weathers: List<Weather>,
        @SerializedName("base") val base: String,
        @SerializedName("main") val main: MainInfo,
        @SerializedName("wind") val wind: Wind?,
        @SerializedName("clouds") val clouds: Clouds?,
        @SerializedName("rain") val rain: Rain?,
        @SerializedName("snow") val snow: Snow?,
        @SerializedName("dt") val timeOfData: Long,
        @SerializedName("sys") val sys: SysInfo,
        @SerializedName("timezone") val timezone: Long,
        @SerializedName("id") val cityId: Long,
        @SerializedName("name") val cityName: String,
        @SerializedName("cod") val cod: Int
    ): ResponseData() {
        data class Coordinates(
            @SerializedName("lon") val longitude: Float,
            @SerializedName("lat") val latitude: Float
        )

        data class Weather(
            @SerializedName("id") val weatherConditionId: Int,
            @SerializedName("main") val weatherGroup: String,
            @SerializedName("description") val weatherDescription: String,
            @SerializedName("icon") val weatherIcon: String
        )
        data class MainInfo(
            @SerializedName("temp") val temperature: Float,
            @SerializedName("feels_like") val feelTemp: Float,
            @SerializedName("temp_max") val maxTemp: Float,
            @SerializedName("temp_min") val minTemp: Float,
            @SerializedName("pressure") val pressure: Int,
            @SerializedName("humidity") val humid: Int,
            @SerializedName("sea_level") val seaLv: Int,
            @SerializedName("grnd_level") val groundLv: Int
        )

        data class Wind(
            @SerializedName("speed") val speed: Float,
            @SerializedName("deg") val degrees: Int
        )

        data class Clouds(
            @SerializedName("all") val cloudiness: Int
        )

        data class Rain(
            @SerializedName("1h") val volumeFor1h: Float,
            @SerializedName("3h") val volumeFor3h: Float
        )

        data class Snow(
            @SerializedName("1h") val volumeFor1h: Float,
            @SerializedName("3h") val volumeFor3h: Float
        )
        data class SysInfo(
            @SerializedName("country") val country: String,
            @SerializedName("sunrise") val sunrise: Long,
            @SerializedName("sunset") val sunset: Long
        )
    }
}
