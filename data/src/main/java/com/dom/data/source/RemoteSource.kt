package com.dom.data.source

import com.dom.data.mapper.DataMapper
import com.dom.data.remote.ApiService
import com.dom.domain.model.Data
import javax.inject.Inject

interface RemoteSource {
    suspend fun getUserList(): List<Data.User>
}

class RemoteSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteSource {

    override suspend fun getUserList(): List<Data.User> {
        return apiService.getUsers().map { DataMapper.toData(it) as Data.User }
    }
}