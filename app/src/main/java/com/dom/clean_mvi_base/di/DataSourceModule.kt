package com.dom.clean_mvi_base.di

import com.dom.data.source.LocalDataSource
import com.dom.data.source.LocalDataSourceImpl
import com.dom.data.source.RemoteSource
import com.dom.data.source.RemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteSource(source: RemoteSourceImpl): RemoteSource = source

    @Provides
    @Singleton
    fun provideLocalSource(source: LocalDataSourceImpl) : LocalDataSource = source
}