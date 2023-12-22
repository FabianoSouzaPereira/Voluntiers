package com.fabianodev.voluntiers.presentation.login

import android.content.Context
import com.fabianodev.voluntiers.R
import com.fabianodev.voluntiers.domain.model.login.LoggedInUserView
import com.fabianodev.voluntiers.domain.model.login.LoginFormState
import com.fabianodev.voluntiers.domain.model.login.LoginResult
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.SignUpWithPassword
import com.fabianodev.voluntiers.domain.usecase.LoginUseCase
import com.fabianodev.voluntiers.utils.PreferenceManager
import io.mockk.coEvery
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LoginViewModelTest {


    /* runTest avoid android.os.Looper not mocked */
    @Test
    fun testValidLogin() = runTest {
        val context = mockk<Context>()
        every { context.getSharedPreferences(any(), any()) } returns mockk(relaxed = true)
        val loginUseCase = mockk<LoginUseCase>()
        val preferenceManager = PreferenceManager(context)

        // Configure the behavior of the mock use case
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
        coEvery { loginUseCase.execute(any(), any(), any()) } returns fakeResponse

        // Create an instance of LoginViewModel with the mock use case
        val loginViewModel = LoginViewModel(context, loginUseCase)

        // Call the login method and verify the result
        loginViewModel.login("fakeUsername", "fakePassword", true)

        // Assert the LiveData values
        assertEquals(
            LoginResult(success = LoggedInUserView(displayName = fakeResponse.email)),
            loginViewModel.loginResult.value
        )
    }

    @Test
    fun testInvalidLogin() = runBlocking {
        val context = mockk<Context>()
        val loginUseCase = mockk<LoginUseCase>()

        // Configure the behavior of the mock use case
        coEvery { loginUseCase.execute(any(), any(), any()) } returns null

        // Create an instance of LoginViewModel with the mock use case
        val loginViewModel = LoginViewModel(context, loginUseCase)

        // Call the login method and verify the result
        loginViewModel.login("fakeUsername", "fakePassword", true)

        // Assert the LiveData values
        assertEquals(LoginResult(error = R.string.login_failed), loginViewModel.loginResult.value)
    }

    @Test
    fun testInvalidUsernameAndPassword() {
        val context = mockk<Context>()
        val loginUseCase = mockk<LoginUseCase>()

        // Create an instance of LoginViewModel with the mock use case
        val loginViewModel = LoginViewModel(context, loginUseCase)

        // Call the loginDataChanged method with invalid username and password
        loginViewModel.loginDataChanged("", "")

        // Assert the LiveData values
        assertEquals(
            LoginFormState(
                usernameError = R.string.invalid_username,
                passwordError = R.string.invalid_password,
                isDataValid = false
            ),
            loginViewModel.loginFormState.value
        )
    }

    // Add more tests based on different scenarios and edge cases
}
