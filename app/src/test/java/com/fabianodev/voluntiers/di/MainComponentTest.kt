package com.fabianodev.voluntiers.di

import com.fabianodev.voluntiers.MainActivity
import com.fabianodev.voluntiers.data.login.RemoteLoginDataSource
import com.fabianodev.voluntiers.di.submodules.MainComponent
import com.fabianodev.voluntiers.presentation.home.HomeFragment
import com.fabianodev.voluntiers.presentation.login.LoginFragment
import com.fabianodev.voluntiers.presentation.login.LoginViewModelTest
import com.fabianodev.voluntiers.presentation.main.MainFragment
import com.fabianodev.voluntiers.presentation.settings.SettingsFragment
import com.fabianodev.voluntiers.presentation.user.UserFragment
import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Fabiano Pereira on 10/01/2024.
 */
@Subcomponent(modules = [MainModuleTest::class])
interface MainComponentTest : MainComponent {
    fun viewModelFactory(): ViewModelFactory

    @OptIn(ExperimentalCoroutinesApi::class)
    fun inject(test: LoginViewModelTest)
    override fun inject(activity: MainActivity)
    override fun inject(fragment: HomeFragment)
    override fun inject(fragment: LoginFragment)
    override fun inject(fragment: MainFragment)
    override fun inject(fragment: SettingsFragment)
    override fun inject(fragment: UserFragment)
    override fun inject(remoteLoginDataSource: RemoteLoginDataSource)
}