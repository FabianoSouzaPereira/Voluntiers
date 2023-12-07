package com.fabianodev.voluntiers.di

import com.fabianodev.voluntiers.data.defaultrepository.DefaultRepositoryImpl
import com.fabianodev.voluntiers.data.home.DefaultHomeRepositoryImpl
import com.fabianodev.voluntiers.data.login.DefaultLoginRepositoryImpl
import com.fabianodev.voluntiers.domain.repositories.HomeRepository
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideDefaultDataSource(repository: DefaultRepositoryImpl): Repository

    @Singleton
    @Binds
    abstract fun provideHomeDataSource(repository: DefaultHomeRepositoryImpl): HomeRepository

    @Singleton
    @Binds
    abstract fun provideLoginDataSource(repository: DefaultLoginRepositoryImpl): LoginRepository
}