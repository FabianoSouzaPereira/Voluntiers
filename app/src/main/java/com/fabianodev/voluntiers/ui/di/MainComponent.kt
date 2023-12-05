package com.fabianodev.voluntiers.ui.di

import com.fabianodev.voluntiers.MainActivity
import com.fabianodev.voluntiers.ui.home.HomeFragment
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