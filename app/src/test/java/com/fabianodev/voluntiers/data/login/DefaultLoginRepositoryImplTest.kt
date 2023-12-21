package com.fabianodev.voluntiers.data.login

import android.content.Context
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.LoggedInUser
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.SignUpWithPassword
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.coroutines.cancellation.CancellationException

class DefaultLoginRepositoryImplTest {

    private val context: Context = mockk()
    private val remoteLoginDataSource: RemoteLoginDataSource = mockk()
    private val loginRepository = DefaultLoginRepositoryImpl(context, remoteLoginDataSource)

    @Test
    fun testLogout() = runBlocking {
        // Set up the behavior of the simulated RemoteLoginDataSource
        coEvery { remoteLoginDataSource.logout(any()) } returns Unit

        // Execute logout and verify if the user is null after logout
        loginRepository.logout("fakeUsername")
        assertNull(loginRepository.user)
    }

    @Test
    fun testLoginSuccess() = runBlocking {
        // Set up the behavior of the simulated RemoteLoginDataSource
        val fakeResponse = SignUpWithPassword.SignUpResponse(
            "fakeDisplayName",
            "fakeEmail",
            "fakeExpiresIn",
            "fakeIdToken",
            "fakeLocalId",
            "fakeRefreshToken",
            true,
            null // No error
        )
        coEvery { remoteLoginDataSource.login(any(), any(), any()) } returns fakeResponse

        // Execute login and verify if the user is correct after login
        val result = loginRepository.login("fakeUsername", "fakePassword", true)
        assertEquals("fakeEmail", result.email)
        assertEquals("fakeDisplayName", result.displayName)
        assertNull(result.error) // Make sure there is no error
    }

    @Test
    fun testLoginFailure() = runBlocking {
        // Set up the behavior of the simulated RemoteLoginDataSource to throw an exception
        coEvery { remoteLoginDataSource.login(any(), any(), any()) } throws CancellationException("Login failed")

        // Execute login and verify if an exception is thrown
        val exception = assertThrows<CancellationException> {
            loginRepository.login("fakeUsername", "fakePassword", true)
        }
        assertEquals("Login failed", exception.message)
    }

    @Test
    fun testSetLoggedInUser() = runBlocking {
        // Set up a fictional user
        val loggedInUser = LoggedInUser("fakeEmail", "fakeDisplayName")

        // Call the setLoggedInUser method
        loginRepository.setLoggedInUser(loggedInUser)

        // Verify if the user has been assigned correctly
        assertEquals(loggedInUser, loginRepository.loggedInUser)
    }
}
