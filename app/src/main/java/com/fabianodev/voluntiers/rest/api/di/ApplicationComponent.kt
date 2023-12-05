package com.fabianodev.voluntiers.rest.api.di

import android.content.Context
import com.fabianodev.voluntiers.ui.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ViewModelBuilder::class, SubComponentsModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationComponent: Context): ApplicationComponent
    }
    fun mainComponent(): MainComponent.Factory
}

@Module(subcomponents = [MainComponent::class])
object SubComponentsModule