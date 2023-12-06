package com.fabianodev.voluntiers.di

import com.fabianodev.voluntiers.repository.DefaultRepository
import com.fabianodev.voluntiers.repository.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideDefaultDataSource(repository: DefaultRepository): Repository
}