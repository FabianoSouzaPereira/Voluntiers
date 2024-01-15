package com.fabianodev.voluntiers.domain.usecase

import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.SignUpWithPassword
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import com.fabianodev.voluntiers.utils.EmailValidator
import javax.inject.Inject


class LoginUseCase @Inject constructor(private val emailValidator: EmailValidator, private val loginRepository: LoginRepository) {

    suspend fun execute(username: String, password: String, returnSecureToken: Boolean): SignUpWithPassword.SignUpResponse? {
        if (!isUserNameValid(username)) {
            println(" Execução: !isUserNameValid(username) in LoginUseCase.execute Return  throw IllegalArgumentException(\"Nome de usuário inválido\")")
            throw IllegalArgumentException("Nome de usuário inválido")
        }

        if (!isPasswordValid(password)) {
            throw IllegalArgumentException("Senha inválida")
        }

        return try {
            loginRepository.login(username, password, returnSecureToken)
        } catch (e: Throwable) {
            null
        }
    }

    fun isUserNameValid(username: String): Boolean {
        return emailValidator.isValidEmail(username)
    }

    fun isNotBlank(username: String): Boolean {
        return emailValidator.isNoBlank(username)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun getIsLoggedIn(): Boolean {
        return loginRepository.isLoggedIn
    }
}
