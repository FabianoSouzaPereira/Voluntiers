package com.fabianodev.voluntiers.di

import com.fabianodev.voluntiers.MainActivity
import com.fabianodev.voluntiers.presentation.home.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)

}