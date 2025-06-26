package com.dom.data.source

import com.dom.data.local.CityNameLogDao
import com.dom.data.mapper.DataMapper
import com.dom.domain.model.Data.CityLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface LocalDataSource {
    fun getAllLogs(): Flow<List<CityLog>>
    fun addLog(log: CityLog)
}

class LocalDataSourceImpl @Inject constructor(
    private val dao: CityNameLogDao
): LocalDataSource {
    override fun getAllLogs(): Flow<List<CityLog>> {
        return dao.getAll().map {
            it.map { logCityName -> DataMapper.toCityLog(logCityName) }
        }
    }

    override fun addLog(log: CityLog) {
        dao.insertAll(DataMapper.toLogCityName(log))
    }

}

