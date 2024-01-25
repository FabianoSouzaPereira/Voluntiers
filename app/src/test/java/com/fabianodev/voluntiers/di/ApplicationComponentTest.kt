package com.fabianodev.voluntiers.di

import android.app.Application
import com.fabianodev.voluntiers.data.di.FirebaseDataSourceModuleTest
import dagger.Component
import dagger.Module
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@Component(modules = [AppModuleTest::class, DataModuleTest::class, SubComponentsModuleTest::class, SubComponentFirebaseModuleTest::class])
class ApplicationComponentTest {

    private lateinit var application: Application
    private lateinit var applicationComponent: ApplicationComponent

    @BeforeEach
    fun setUp() {
        application = mockk(relaxed = true)
        applicationComponent = DaggerApplicationComponent.factory().create(application)
    }

    @Test
    fun `mainComponent factory should provide MainComponent`() {
        val mainComponentFactory = applicationComponent.mainComponent()
        assertNotNull(mainComponentFactory, "MainComponent.Factory should not be null")
    }
}

@Module(subcomponents = [MainComponentTest::class])
object SubComponentsModuleTest

@Module(subcomponents = [FirebaseDataSourceModuleTest::class])
object SubComponentFirebaseModuleTest