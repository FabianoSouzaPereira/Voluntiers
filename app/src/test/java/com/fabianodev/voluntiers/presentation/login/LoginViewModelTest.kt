package com.fabianodev.voluntiers.presentation.login

import android.content.Context
import android.content.SharedPreferences
import com.fabianodev.voluntiers.domain.model.login.LoggedInUserView
import com.fabianodev.voluntiers.domain.model.login.LoginResult
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.SignUpWithPassword
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import com.fabianodev.voluntiers.domain.usecase.LoginUseCase
import com.fabianodev.voluntiers.utils.EmailValidator
import com.fabianodev.voluntiers.utils.PreferenceManager
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    private lateinit var context: Context
    private lateinit var loginRepository: LoginRepository
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var emailValidator: EmailValidator
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var loginViewModel: LoginViewModel
    private val fakeResponse = SignUpWithPassword.SignUpResponse(
            "fakeDisplayName",
            "fakemail@testemail.com",
            "fakeExpiresIn",
            "fakeIdToken",
            "fakeLocalId",
            "fakeRefreshToken",
            true,
            null // No error
    )

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        // Mock Context and SharedPreferences
        val mockSharedPreferences = mockk<SharedPreferences>(relaxed = true)
        val mockEditor = mockk<SharedPreferences.Editor>(relaxed = true)
        context = mockk(relaxed = true)
        every { context.getSharedPreferences("Preferences", Context.MODE_PRIVATE) } returns mockSharedPreferences
        every { mockSharedPreferences.edit() } returns mockEditor

        // Mock other dependencies
        loginRepository = mockk()
        emailValidator = mockk()
        preferenceManager = PreferenceManager(context)  // Use the real PreferenceManager with mocked Context
        loginUseCase = LoginUseCase(emailValidator, loginRepository)

        // Create a real instance of LoginViewModel with mocked dependencies
        loginViewModel = LoginViewModel(context, loginUseCase)

        // Configure mocks for LoginUseCase

        coEvery { loginUseCase.isUserNameValid(any()) } returns true
        coEvery { loginUseCase.execute(any(), any(), any()) } returns fakeResponse
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test isUserNameValid returns true`() = runTest {
        assert(loginUseCase.isUserNameValid("fakemail@testemail.com"))
    }


    @Test
    fun `test valid login updates LiveData with success`() = runTest {
        println("Before login: isUserNameValid(any()) should return true")

        loginViewModel.login("fakemail@testemail.com", "fakePassword", true)

        println("After login: isUserNameValid(any()) returned ${loginUseCase.isUserNameValid("fakemail@testemail.com")}")

        val expectedValue = LoginResult(success = LoggedInUserView(displayName = fakeResponse.email))
        assert(loginViewModel.loginResult.value == expectedValue)
    }
}
