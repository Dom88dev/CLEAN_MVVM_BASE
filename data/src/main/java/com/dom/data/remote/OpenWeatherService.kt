package com.dom.data.remote

import com.dom.data.model.WeatherRes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("weather")
    suspend fun getByCityName(@Query("q") cityName: String, @Query("appid") apiKey: String = WEATHER_API_KEY, @Query("lang") lang: String = "kr"): Response<WeatherRes>

    @GET("weather")
    fun getByCityId(@Query("id") cityId: String, @Query("appid") apiKey: String = WEATHER_API_KEY, @Query("lang") lang: String = "kr"): Response<WeatherRes>

    @GET("weather")
    fun getByCoordinates(@Query("lat") lat: Long, @Query("lon") lon: Long, @Query("appid") apiKey: String = WEATHER_API_KEY, @Query("lang") lang: String = "kr"): Response<WeatherRes>

    companion object {
        //open.weather app의 api를 이용하기 위한 키
        const val WEATHER_API_KEY = "82b05521b26b350f40d09506274dcbe8"
    }
}