package com.dom.data.mapper

import com.dom.data.model.ApiResult
import com.dom.data.model.Entities
import com.dom.domain.model.Data

object DataMapper {

    fun toData(res: ApiResult): Data {
        return when (res) {
            is ApiResult.UserDto -> {
                toUser(res)
            }

            else -> Data.NoData
        }
    }

    fun toUser(data: ApiResult.UserDto): Data.User {
        return Data.User(
            data.id,
            data.name,
            data.userName,
            data.email,
            "(${data.address.zip}) ${data.address.suite}, ${data.address.st}, ${data.address.city}",
            data.phone,
            data.webSite,
            Data.Company(data.company.name, data.company.catchPhrase, data.company.bs)
        )
    }

    fun toCityLog(data: Entities.LogCityName): Data.CityLog =
        Data.CityLog(data.name, data.timestamp)

    fun toLogCityName(data: Data.CityLog): Entities.LogCityName {
        val result = Entities.LogCityName(data.name)
        result.timestamp = data.timestamp
        return result
    }
}