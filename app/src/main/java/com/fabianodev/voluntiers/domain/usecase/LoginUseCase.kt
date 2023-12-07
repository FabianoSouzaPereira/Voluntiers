package com.fabianodev.voluntiers.domain.usecase

import com.fabianodev.voluntiers.domain.model.User
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend fun execute(username: String, password: String): User {
        return loginRepository.login(username, password)
    }
}