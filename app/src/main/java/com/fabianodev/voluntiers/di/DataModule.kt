package com.fabianodev.voluntiers.di

import com.fabianodev.voluntiers.dao.api.data.rest.ILoginApiService
import com.fabianodev.voluntiers.dao.api.data.rest.IUserApiService
import com.fabianodev.voluntiers.data.home.DefaultHomeRepositoryImpl
import com.fabianodev.voluntiers.data.login.DefaultLoginRepositoryImpl
import com.fabianodev.voluntiers.data.user.RemoteUserDataSource
import com.fabianodev.voluntiers.domain.repositories.HomeRepository
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import com.fabianodev.voluntiers.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Binds
    abstract fun bindUserRepository(remoteUserDataSource: RemoteUserDataSource): UserRepository

    @Singleton
    @Binds
    abstract fun bindHomeRepository(repository: DefaultHomeRepositoryImpl): HomeRepository

    @Singleton
    @Binds
    abstract fun bindLoginRepository(repository: DefaultLoginRepositoryImpl): LoginRepository

    companion object {
        @Provides
        @Singleton
        fun provideUserApiService(): IUserApiService {
            return Retrofit.Builder()
                .baseUrl("https://example.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IUserApiService::class.java)
        }

        @Provides
        @Singleton
        fun provideLoginApiService(): ILoginApiService {
            return Retrofit.Builder()
                .baseUrl("https://example.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ILoginApiService::class.java)
        }
    }
}
