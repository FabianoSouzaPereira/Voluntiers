package com.fabianodev.voluntiers.di

import android.content.Context
import com.fabianodev.voluntiers.data.di.FirebaseDataSourceModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ViewModelBuilder::class, SubComponentsModule::class, SubComponentFirebaseModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationComponent: Context): ApplicationComponent
    }

    fun mainComponent(): MainComponent.Factory
}

@Module(subcomponents = [MainComponent::class])
object SubComponentsModule

@Module(subcomponents = [FirebaseDataSourceModule::class])
object SubComponentFirebaseModule