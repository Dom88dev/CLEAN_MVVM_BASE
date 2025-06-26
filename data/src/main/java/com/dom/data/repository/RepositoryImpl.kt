package com.dom.data.repository

import com.dom.data.annotation.BackTask
import com.dom.data.source.LocalDataSource
import com.dom.data.source.RemoteSource
import com.dom.domain.model.Data
import com.dom.domain.model.Data.CityLog
import com.dom.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteSource: RemoteSource,
    private val localDataSource: LocalDataSource,
    @BackTask private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : Repository {

    override suspend fun getUserList(): List<Data.User> {
        return remoteSource.getUserList()
    }
}