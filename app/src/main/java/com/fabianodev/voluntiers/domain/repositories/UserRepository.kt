package com.fabianodev.voluntiers.domain.repositories

import com.fabianodev.voluntiers.domain.model.User

interface UserRepository {
    suspend fun getUserById(userId: String): User?
}