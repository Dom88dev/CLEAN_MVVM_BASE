package com.dom.domain.model

data class WeatherInfo(
    val id: Long,
    val temp: Float,
    val tempMax: Float,
    val tempMin: Float,
    val humid: Int,
    val state: String,
    val icon: String
): Data()
