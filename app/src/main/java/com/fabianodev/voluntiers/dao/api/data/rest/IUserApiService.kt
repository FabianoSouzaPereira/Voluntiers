package com.fabianodev.voluntiers.dao.api.data.rest

import com.fabianodev.voluntiers.domain.model.user.User
import retrofit2.http.GET
import retrofit2.http.Path

interface IUserApiService {
    @GET("user/{userId}")
    suspend fun getUserById(@Path("userId") userId: String): User
}