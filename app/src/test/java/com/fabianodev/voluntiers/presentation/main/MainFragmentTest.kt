package com.fabianodev.voluntiers.presentation.main

import android.content.Context
import androidx.navigation.NavController
import com.fabianodev.voluntiers.utils.PreferenceManager
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainFragmentTest {
    private lateinit var navController: NavController
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var token: String
    private lateinit var context: Context

    @BeforeEach
    fun setUp() {
        context = mockk(relaxed = true)
        navController = mockk(relaxed = true)
        preferenceManager = PreferenceManager(context)
        every { context.getSharedPreferences("Preferences", 0) } returns preferenceManager.getPreferencesInstance()
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun onAttach() {
        token = preferenceManager.getPreferenceString("token", "")
        assertNotNull(token, "O token não deve ser nulo")
    }

    @Test
    fun onCreate() {
    }

    @Test
    fun onCreateView() {
    }

    @Test
    fun newInstance() {
    }
}