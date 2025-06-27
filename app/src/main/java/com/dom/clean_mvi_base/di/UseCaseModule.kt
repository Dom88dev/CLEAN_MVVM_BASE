package com.dom.clean_mvi_base.di

import com.dom.domain.repository.Repository
import com.dom.domain.usecase.UseCaseUsers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCaseUsers(repository: Repository): UseCaseUsers = UseCaseUsers(repository)
}