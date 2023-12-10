package com.fabianodev.voluntiers.di

import com.fabianodev.voluntiers.dao.api.data.rest.IAuthApiService
import com.fabianodev.voluntiers.dao.api.data.rest.IHomeApiService
import com.fabianodev.voluntiers.dao.api.data.rest.IUserApiService
import com.fabianodev.voluntiers.dao.api.data.rest.RetrofitInitializer
import com.fabianodev.voluntiers.data.home.DefaultHomeRepositoryImpl
import com.fabianodev.voluntiers.data.login.DefaultLoginRepositoryImpl
import com.fabianodev.voluntiers.data.user.RemoteUserDataSource
import com.fabianodev.voluntiers.domain.repositories.HomeRepository
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import com.fabianodev.voluntiers.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
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
        fun provideRetrofitInitializer(): RetrofitInitializer.Companion {
            return RetrofitInitializer
        }

        @Provides
        @Singleton
        fun provideHomeApiService(retrofitInitializer: RetrofitInitializer.Companion): IHomeApiService {
            return retrofitInitializer.getRetrofitInstance("https://example.com/api/").create(IHomeApiService::class.java)
        }

        @Provides
        @Singleton
        fun provideUserApiService(retrofitInitializer: RetrofitInitializer.Companion): IUserApiService {
            return retrofitInitializer.getRetrofitInstance("https://example.com/api/").create(IUserApiService::class.java)
        }

        @Provides
        @Singleton
        fun provideAuthApiService(retrofitInitializer: RetrofitInitializer.Companion): IAuthApiService {
            return retrofitInitializer.getRetrofitInstance("https://example.com/api/").create(IAuthApiService::class.java)
        }
    }
}
