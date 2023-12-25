package com.fabianodev.voluntiers.presentation.login

import android.content.Context
import android.content.SharedPreferences
import com.fabianodev.voluntiers.R
import com.fabianodev.voluntiers.domain.model.login.LoggedInUserView
import com.fabianodev.voluntiers.domain.model.login.LoginFormState
import com.fabianodev.voluntiers.domain.model.login.LoginResult
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.SignUpWithPassword
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import com.fabianodev.voluntiers.domain.usecase.LoginUseCase
import com.fabianodev.voluntiers.utils.EmailValidator
import com.fabianodev.voluntiers.utils.PreferenceManager
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LoginViewModelTest {
    private lateinit var context: Context
    private lateinit var loginRepository: LoginRepository
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var emailValidator: EmailValidator
    private lateinit var preferenceManager: PreferenceManager

    @BeforeEach
    fun setUp() {
        context = mockk()
        val mockSharedPreferences = mockk<SharedPreferences>(relaxed = true)
        every { context.getSharedPreferences(any(), any()) } returns mockSharedPreferences
        loginRepository = mockk()
        emailValidator = mockk()
        loginUseCase = LoginUseCase(emailValidator, loginRepository)
        preferenceManager = PreferenceManager(context)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }


    /* runTest avoid android.os.Looper not mocked */
    @Test
    fun testValidLogin() = runTest {
        // Configure the behavior of the mock use case
        coEvery { loginUseCase.isUserNameValid(any()) } returns true

        val fakeResponse = SignUpWithPassword.SignUpResponse(
            "fakeDisplayName",
            "fakemail@testemail.com",
            "fakeExpiresIn",
            "fakeIdToken",
            "fakeLocalId",
            "fakeRefreshToken",
            true,
            null // No error
        )

        // Configure the behavior of the mock use case to return the fake response
        coEvery { loginUseCase.execute(any(), any(), any()) } returns fakeResponse

        // Create an instance of LoginViewModel with the mock use case
        val loginViewModel = LoginViewModel(context, loginUseCase)

        // Call the login method and verify the result
        loginViewModel.login("fakemail@testemail.com", "fakePassword", true)

        // Assert the LiveData values
        assertEquals(
            LoginResult(success = LoggedInUserView(displayName = fakeResponse.email)),
            loginViewModel.loginResult.value
        )

        // Verify that isUserNameValid was called with the correct argument
        coVerify { loginUseCase.isUserNameValid("fakemail@testemail.com") }

        // Verify that execute was called with the correct arguments
        coVerify { loginUseCase.execute("fakemail@testemail.com", "fakePassword", true) }
    }


    @Test
    fun testInvalidLogin() = runTest {
        val context = mockk<Context>()
        val loginUseCase = mockk<LoginUseCase>()

        // Configura o comportamento do caso de uso mockado
        coEvery { context.getSharedPreferences("Preferences", 0) } returns preferenceManager.getPreferencesInstance()
        coEvery { loginUseCase.execute(any(), any(), any()) } throws IllegalArgumentException("Nome de usuário inválido")

        // Cria uma instância de LoginViewModel com o caso de uso mockado
        val loginViewModel = LoginViewModel(context, loginUseCase)

        // Chama o método de login e verifica o resultado
        loginViewModel.login("fakeUsername", "fakePassword", true)

        // Assert nos valores LiveData
        assertEquals(LoginResult(error = R.string.login_failed), loginViewModel.loginResult.value)
    }


    @Test
    fun testInvalidUsernameAndPassword() {
        val context = mockk<Context>()
        val loginUseCase = mockk<LoginUseCase>()

        coEvery { loginUseCase.execute(any(), any(), any()) } throws IllegalArgumentException("Senha inválida")

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
