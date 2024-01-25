package com.fabianodev.voluntiers.di

import android.content.Context
import com.fabianodev.voluntiers.dao.api.data.rest.IAuthApiService
import com.fabianodev.voluntiers.dao.api.data.rest.IHomeApiService
import com.fabianodev.voluntiers.dao.api.data.rest.IUserApiService
import com.fabianodev.voluntiers.dao.api.data.rest.RetrofitInitializer
import com.fabianodev.voluntiers.data.home.DefaultHomeRepositoryImpl
import com.fabianodev.voluntiers.data.login.DefaultLoginRepositoryImpl
import com.fabianodev.voluntiers.data.user.DefaultUserRepositoryImpl
import com.fabianodev.voluntiers.domain.repositories.HomeRepository
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import com.fabianodev.voluntiers.domain.repositories.UserRepository
import com.fabianodev.voluntiers.utils.Constants.Endpoint.BASE_URL
import com.fabianodev.voluntiers.utils.PreferenceManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(repository: DefaultUserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindHomeRepository(repository: DefaultHomeRepositoryImpl): HomeRepository

    @Singleton
    @Binds
    abstract fun bindLoginRepository(repository: DefaultLoginRepositoryImpl): LoginRepository

    companion object DataModuleObject {
        @Provides
        @Singleton
        fun provideRetrofitInitializer(): RetrofitInitializer.Companion {
            return RetrofitInitializer
        }

        @Provides
        @Singleton
        fun provideHomeApiService(context: Context, retrofitInitializer: RetrofitInitializer.Companion): IHomeApiService {
            return retrofitInitializer.getRetrofitInstance(BASE_URL, getToken(context)).create(IHomeApiService::class.java)
        }

        @Provides
        @Singleton
        fun provideUserApiService(context: Context, retrofitInitializer: RetrofitInitializer.Companion): IUserApiService {
            return retrofitInitializer.getRetrofitInstance(BASE_URL, getToken(context)).create(IUserApiService::class.java)
        }

        @Provides
        @Singleton
        fun provideAuthApiService(context: Context, retrofitInitializer: RetrofitInitializer.Companion): IAuthApiService {
            return retrofitInitializer.getRetrofitInstance(BASE_URL, getToken(context)).create(IAuthApiService::class.java)
        }

        private fun getToken(context: Context): String {
            return PreferenceManager(context).getPreferenceString("token", "")
        }
    }
}
