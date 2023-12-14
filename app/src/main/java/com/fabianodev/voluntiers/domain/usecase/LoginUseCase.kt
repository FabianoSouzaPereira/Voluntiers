package com.fabianodev.voluntiers.domain.usecase

import android.util.Patterns
import com.fabianodev.voluntiers.domain.model.User
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun execute(username: String, password: String, returnSecureToken: Boolean): User? {
        if (!isUserNameValid(username)) {
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
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
