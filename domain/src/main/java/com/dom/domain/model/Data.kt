package com.dom.domain.model

sealed class Data {

    object NoData: Data()
    data class CityLog(
        val name: String,
        val timestamp: Long
    ): Data()

    data class WeatherInfo(
        val id: Long,
        val temp: Float,
        val tempMax: Float,
        val tempMin: Float,
        val humid: Int,
        val state: String,
        val icon: String
    ): Data()

    companion object {
        enum class ListDataType {
            BankCode
        }
    }
}