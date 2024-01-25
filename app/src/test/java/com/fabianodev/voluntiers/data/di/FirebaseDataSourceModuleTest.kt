package com.fabianodev.voluntiers.data.di

import dagger.Subcomponent

/**
 * Created by Fabiano Pereira on 25/01/2024.
 */
@Subcomponent(modules = [FirebaseModuleTest::class])
interface FirebaseDataSourceModuleTest {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FirebaseDataSourceModule
    }

    fun inject(firebaseDataSourceModuleTest: FirebaseDataSourceModuleTest)
}