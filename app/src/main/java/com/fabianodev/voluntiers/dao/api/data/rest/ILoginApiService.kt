package com.fabianodev.voluntiers.dao.api.data.rest

import com.fabianodev.voluntiers.dao.entities.LoginRequest
import com.fabianodev.voluntiers.domain.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface ILoginApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): User
}
