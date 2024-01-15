package com.fabianodev.voluntiers.di

import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DataModuleTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun bindUserRepository() {
    }

    @Test
    fun bindHomeRepository() {
    }

    @Test
    fun bindLoginRepository() {
    }
}