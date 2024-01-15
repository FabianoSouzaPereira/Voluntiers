package com.fabianodev.voluntiers.data.user

import com.fabianodev.voluntiers.domain.model.user.User
import com.fabianodev.voluntiers.domain.repositories.UserRepository
import javax.inject.Inject

class DefaultUserRepositoryImpl @Inject constructor(private val remoteUserDataSource: RemoteUserDataSource) : UserRepository {
    override suspend fun getUserById(userId: String): Result<User?> {
        return remoteUserDataSource.getUserById(userId)
    }
}