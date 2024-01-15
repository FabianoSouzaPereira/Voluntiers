package com.fabianodev.voluntiers.di

import android.content.Context
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ApplicationComponentTest {

    private lateinit var context: Context
    private lateinit var applicationComponent: ApplicationComponent

    @BeforeEach
    fun setUp() {
        context = mockk(relaxed = true)
        applicationComponent = DaggerApplicationComponent.factory().create(context)
    }

    @Test
    fun `mainComponent factory should provide MainComponent`() {
        val mainComponentFactory = applicationComponent.mainComponent()
        assertNotNull(mainComponentFactory, "MainComponent.Factory should not be null")
    }
}
