package com.fabianodev.voluntiers.di

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModuleTest {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = ApplicationProvider.getApplicationContext()
}