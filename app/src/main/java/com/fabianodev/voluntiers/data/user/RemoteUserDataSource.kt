package com.fabianodev.voluntiers.data.user

import com.fabianodev.voluntiers.dao.api.data.rest.IUserApiService
import com.fabianodev.voluntiers.domain.model.user.User
import com.fabianodev.voluntiers.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteUserDataSource @Inject constructor(private val userApiService: IUserApiService) : UserRepository {
    override suspend fun getUserById(userId: String): Result<User?> {
        return try {
            val user = withContext(Dispatchers.IO) {
                userApiService.getUserById(userId)
            }
            Result.success(user)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}
