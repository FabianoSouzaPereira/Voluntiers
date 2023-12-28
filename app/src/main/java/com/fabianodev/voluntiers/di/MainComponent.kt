package com.fabianodev.voluntiers.di

import com.fabianodev.voluntiers.MainActivity
import com.fabianodev.voluntiers.data.login.RemoteLoginDataSource
import com.fabianodev.voluntiers.presentation.home.HomeFragment
import com.fabianodev.voluntiers.presentation.login.LoginFragment
import com.fabianodev.voluntiers.presentation.main.MainFragment
import com.fabianodev.voluntiers.presentation.settings.SettingsFragment
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: MainFragment)
    fun inject(fragment: SettingsFragment)
    fun inject(remoteLoginDataSource: RemoteLoginDataSource)
}