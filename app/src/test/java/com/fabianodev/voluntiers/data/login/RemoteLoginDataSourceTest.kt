package com.fabianodev.voluntiers.data.login

import com.fabianodev.voluntiers.dao.api.data.rest.IAuthApiService
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.SignUpWithPassword
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.coroutines.cancellation.CancellationException

class RemoteLoginDataSourceTest {

    private val authApiService: IAuthApiService = mockk()
    private val remoteLoginDataSource = RemoteLoginDataSource(authApiService)

    @Test
    fun testLogout() = runBlocking {
        // Set up the behavior of the simulated AuthApiService
        coEvery { authApiService.logout(any()) } returns Unit

        // Execute logout and verify if the user is null after logout
        remoteLoginDataSource.logout("fakeUsername")
        assertNull(remoteLoginDataSource.user)
    }

    @Test
    fun testLoginSuccess() = runBlocking {
        // Set up the behavior of the simulated AuthApiService
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
        coEvery { authApiService.login(any()) } returns fakeResponse

        // Execute login and verify if the user is correct after login
        val result = remoteLoginDataSource.login("fakeUsername", "fakePassword", true)
        assertEquals("fakeEmail", result.email)
        assertEquals("fakeDisplayName", result.displayName)
        assertNull(result.error) // Make sure there is no error
    }

    @Test
    fun testLoginFailure() = runBlocking {
        // Set up the behavior of the simulated AuthApiService to throw an exception
        coEvery { authApiService.login(any()) } throws CancellationException("Login failed")

        // Execute login and verify if an exception is thrown
        val exception = assertThrows<CancellationException> {
            remoteLoginDataSource.login("fakeUsername", "fakePassword", true)
        }
        assertEquals("Login failed", exception.message)
    }
}
