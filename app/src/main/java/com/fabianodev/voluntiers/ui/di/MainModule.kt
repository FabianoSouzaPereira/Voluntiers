package com.fabianodev.voluntiers.ui.di

import androidx.lifecycle.ViewModel
import com.fabianodev.voluntiers.rest.api.di.ViewModelKey
import com.fabianodev.voluntiers.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

}