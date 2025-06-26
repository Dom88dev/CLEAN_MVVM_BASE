package com.dom.clean_mvvm_base.di

import com.dom.data.repository.RepositoryImpl
import com.dom.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(repository: RepositoryImpl) : Repository = repository
}