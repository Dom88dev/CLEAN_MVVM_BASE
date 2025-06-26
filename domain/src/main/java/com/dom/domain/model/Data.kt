package com.dom.domain.model

import java.io.Serializable

sealed class Data : Serializable {

    object NoData: Data()

    data class User(
        val id: Long,
        val name: String,
        val userName: String,
        val email: String,
        val address: String,
        val phone: String,
        val webSite: String,
        val company: Company,
    ): Data(), Serializable

    data class Company(
        val name: String,
        val catchPhrase: String,
        val bs: String
    ): Data(), Serializable

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