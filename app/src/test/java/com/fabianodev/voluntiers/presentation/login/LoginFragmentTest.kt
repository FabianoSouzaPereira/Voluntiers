package com.fabianodev.voluntiers.presentation.login

import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LoginFragmentTest {

    private lateinit var context: Context

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }


    @Test
    fun getViewModelFactory() {
    }

    @Test
    fun setViewModelFactory() {
    }

    @Test
    fun getUsernameEditText() {
    }

    @Test
    fun setUsernameEditText() {
    }

    @Test
    fun getPasswordEditText() {
    }

    @Test
    fun setPasswordEditText() {
    }

    @Test
    fun getLoginButton() {
    }

}