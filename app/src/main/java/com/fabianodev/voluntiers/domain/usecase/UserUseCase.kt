package com.fabianodev.voluntiers.domain.usecase

import com.fabianodev.voluntiers.domain.model.user.User
import com.fabianodev.voluntiers.domain.repositories.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun execute(userId: String): Result<User?> {
        return try {
            userRepository.getUserById(userId)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}