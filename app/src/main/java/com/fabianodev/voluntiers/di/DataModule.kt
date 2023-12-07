package com.fabianodev.voluntiers.di

import com.fabianodev.voluntiers.data.defaultrepository.IRepositoryImpl
import com.fabianodev.voluntiers.data.home.IHomeRepositoryImpl
import com.fabianodev.voluntiers.data.login.ILoginRepositoryImpl
import com.fabianodev.voluntiers.domain.repositories.IHomeRepository
import com.fabianodev.voluntiers.domain.repositories.ILoginRepository
import com.fabianodev.voluntiers.domain.repositories.IRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideDefaultDataSource(repository: IRepositoryImpl): IRepository

    @Singleton
    @Binds
    abstract fun provideHomeDataSource(repository: IHomeRepositoryImpl): IHomeRepository

    @Singleton
    @Binds
    abstract fun provideLoginDataSource(repository: ILoginRepositoryImpl): ILoginRepository
}