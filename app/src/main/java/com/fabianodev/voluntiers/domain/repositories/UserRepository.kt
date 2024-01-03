package com.fabianodev.voluntiers.domain.repositories

import com.fabianodev.voluntiers.domain.model.user.User

interface UserRepository {
    suspend fun getUserById(userId: String): Result<User?>
}