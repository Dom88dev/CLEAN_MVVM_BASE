package com.dom.clean_mvvm_base.di

import com.dom.data.source.LogDataSource
import com.dom.data.source.LogLocalDataSource
import com.dom.data.source.OpenWeatherRemoteSource
import com.dom.data.source.OpenWeatherRemoteSourceImpl
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
    fun provideOpenWeatherRemoteSource(source: OpenWeatherRemoteSourceImpl): OpenWeatherRemoteSource = source

    @Provides
    @Singleton
    fun provideLogSource(source: LogLocalDataSource) : LogDataSource = source
}