package com.dom.data.source

import com.dom.data.local.CityNameLogDao
import com.dom.data.mapper.WeatherDataMapper
import com.dom.domain.model.Data.CityLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface LogDataSource {
    fun getAllLogs(): Flow<List<CityLog>>
    fun addLog(log: CityLog)
}

class LogLocalDataSource @Inject constructor(
    private val dao: CityNameLogDao
): LogDataSource {
    override fun getAllLogs(): Flow<List<CityLog>> {
        return dao.getAll().map {
            it.map { logCityName -> WeatherDataMapper.toCityLog(logCityName) }
        }
    }

    override fun addLog(log: CityLog) {
        dao.insertAll(WeatherDataMapper.toLogCityName(log))
    }

}

