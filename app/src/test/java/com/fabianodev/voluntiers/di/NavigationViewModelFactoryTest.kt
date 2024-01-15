package com.fabianodev.voluntiers.di

import androidx.lifecycle.ViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.inject.Provider

class NavigationViewModelFactoryTest {

    private lateinit var viewModelFactory: NavigationViewModelFactory
    private lateinit var mockCreators: Map<Class<out ViewModel>, Provider<ViewModel>>

    @BeforeEach
    fun setUp() {
        mockCreators = mockk(relaxed = true)
        viewModelFactory = NavigationViewModelFactory(mockCreators)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `create should return correct ViewModel instance`() {
        val mockViewModel: ViewModel = mockk()
        val mockProvider: Provider<ViewModel> = Provider { mockViewModel }
        every { mockCreators[any<Class<ViewModel>>()] } returns mockProvider

        // Act: Request a ViewModel instance from the factory
        val viewModelInstance = viewModelFactory.create(ViewModel::class.java)

        // Assert: Check if the correct ViewModel instance is returned
        assertSame(mockViewModel, viewModelInstance)
    }

    @Test
    fun `create should throw exception when no provider found`() {
        // Configurar o mapa de criadores para retornar null
        every { mockCreators[any<Class<ViewModel>>()] } returns null

        // Act and Assert: Espera que uma exceção seja lançada
        assertThrows<IllegalArgumentException> {
            viewModelFactory.create(ViewModel::class.java)
        }
    }
}
