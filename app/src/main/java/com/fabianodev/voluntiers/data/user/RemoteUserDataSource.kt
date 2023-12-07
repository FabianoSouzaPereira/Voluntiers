package com.fabianodev.voluntiers.data.user

import com.fabianodev.voluntiers.dao.api.data.rest.IUserApiService
import com.fabianodev.voluntiers.domain.model.User
import com.fabianodev.voluntiers.domain.repositories.UserRepository
import javax.inject.Inject

class RemoteUserDataSource @Inject constructor(private val userApiService: IUserApiService) : UserRepository {
    override suspend fun getUserById(userId: String): User? {
        try {
            return userApiService.getUserById(userId)
        } catch (e: Exception) {
            // Tratar exceções, como falta de conectividade ou erro na chamada
            return null
        }
    }

}
