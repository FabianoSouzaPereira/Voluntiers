package com.fabianodev.voluntiers.data.di

import dagger.Subcomponent

@Subcomponent(modules = [FirebaseModule::class])
interface FirebaseDataSourceModule {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FirebaseDataSourceModule
    }

    fun inject(firebaseDataSourceModule: FirebaseDataSourceModule)
}