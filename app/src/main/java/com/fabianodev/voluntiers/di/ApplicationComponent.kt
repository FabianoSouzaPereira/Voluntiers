package com.fabianodev.voluntiers.di

import android.app.Application
import com.fabianodev.voluntiers.data.di.FirebaseDataSourceModule
import com.fabianodev.voluntiers.di.submodules.MainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, ViewModelBuilder::class, SubComponentsModule::class, SubComponentFirebaseModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

    fun mainComponent(): MainComponent.Factory
}

@Module(subcomponents = [MainComponent::class])
object SubComponentsModule

@Module(subcomponents = [FirebaseDataSourceModule::class])
object SubComponentFirebaseModule