package com.fabianodev.voluntiers.di.submodules

import androidx.lifecycle.ViewModel
import com.fabianodev.voluntiers.di.ViewModelKey
import com.fabianodev.voluntiers.presentation.home.HomeViewModel
import com.fabianodev.voluntiers.presentation.login.LoginViewModel
import com.fabianodev.voluntiers.presentation.user.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    fun bindUserViewModel(viewModel: UserViewModel): ViewModel
}