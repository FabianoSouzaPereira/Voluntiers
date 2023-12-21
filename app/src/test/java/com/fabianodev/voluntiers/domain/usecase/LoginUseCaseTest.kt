package com.fabianodev.voluntiers.domain.usecase

import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.SignUpWithPassword
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import com.fabianodev.voluntiers.utils.EmailValidator
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LoginUseCaseTest {

    private lateinit var loginRepository: LoginRepository
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var emailValidator: EmailValidator

    @BeforeEach
    fun setUp() {
        loginRepository = mockk()
        emailValidator = mockk()
        loginUseCase = LoginUseCase(emailValidator, loginRepository)
    }

    @Test
    fun testValidLogin() = runBlocking {
        // Configure the behavior do mock do EmailValidator
        coEvery { emailValidator.isValidEmail(any()) } returns true
        // Configure the behavior of the mock repository
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
        coEvery { loginRepository.login(any(), any(), any()) } returns fakeResponse

        // Call the execute method and verify the result
        val result = loginUseCase.execute("fakeUsername", "fakePassword", true)
        assertNotNull(result)
        assertEquals("fakeEmail", result!!.email)
        assertEquals("fakeDisplayName", result.displayName)
        // Add more assertions based on the expected behavior of your LoginUseCase
    }

    @Test
    fun testInvalidUsername() {
        // Configure the behavior do mock do EmailValidator
        coEvery { emailValidator.isValidEmail(any()) } returns false
        // Call the execute method with an invalid username and expect an IllegalArgumentException
        val exception = assertThrows<java.lang.IllegalArgumentException> {
            runBlocking {
                loginUseCase.execute("", "fakePassword", true)
            }
        }

        assertEquals("Nome de usuário inválido", exception.message)
    }


    @Test
    fun testInvalidPassword() {
        // Call the execute method with an invalid password and expect an IllegalArgumentException
        assertThrows<io.mockk.MockKException> {
            runBlocking {
                loginUseCase.execute("fakeUsername", "123", true)
            }
        }
    }

    @Test
    fun testRepositoryException() {
        // Configure the behavior of the mock repository to throw an exception
        coEvery { loginRepository.login(any(), any(), any()) } throws Exception("Login failed")

        // Configure the behavior of the email validator for the specific case
        coEvery { emailValidator.isValidEmail("fakeUsername") } returns false

        // Call the execute method and verify that it returns null
        val exception = assertThrows<java.lang.IllegalArgumentException> {
            runBlocking {
                loginUseCase.execute("fakeUsername", "fakePassword", true)
            }
        }
        assertEquals("Nome de usuário inválido", exception.message)
    }


    @Test
    fun testIsUserNameValid() {
        // Mock the email validator
        coEvery { emailValidator.isValidEmail(any()) } returns true

        // Call the isUserNameValid method with a valid email address
        val isValidEmail = loginUseCase.isUserNameValid("fake@example.com")
        assertTrue(isValidEmail)

        // Call the isUserNameValid method with an invalid email address
        //  val isInvalidEmail = loginUseCase.isNotBlank("")
        //  assertFalse(isInvalidEmail)

        // Call the isUserNameValid method with a non-email username
//        val isValidNotBlankEmail = loginUseCase.isNotBlank("fakeUsername")
//        assertTrue(isValidNotBlankEmail)
    }

    @Test
    fun testIsUserNameInvalid() {
        // Mock the email validator to return false
        coEvery { emailValidator.isValidEmail(any()) } returns false

        // Call the isUserNameValid method with a valid email address
        val isValidEmail = loginUseCase.isUserNameValid("fake@example.com")
        assertFalse(isValidEmail)

        // Call the isUserNameValid method with an invalid email address
        val isInvalidEmail = loginUseCase.isUserNameValid("invalid_email")
        assertFalse(isInvalidEmail)
    }


    @Test
    fun testIsPasswordValid() {
        // Call the isPasswordValid method with a valid password and expect true
        assertTrue(loginUseCase.isPasswordValid("securePassword"))

        // Call the isPasswordValid method with an invalid password and expect false
        assertFalse(loginUseCase.isPasswordValid("123"))
    }

    @Test
    fun testGetIsLoggedIn() {
        // Configure the behavior of the mock repository
        coEvery { loginRepository.isLoggedIn } returns true

        // Call the getIsLoggedIn method and expect true
        assertTrue(loginUseCase.getIsLoggedIn())
    }
}
