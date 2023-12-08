package com.fabianodev.voluntiers.dao.api.data.rest

import com.fabianodev.voluntiers.dao.entities.LoginRequest
import com.fabianodev.voluntiers.data.login.DataResult
import com.fabianodev.voluntiers.domain.model.login.LoggedInUser
import retrofit2.http.Body
import retrofit2.http.POST

interface ILoginApiService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): DataResult<LoggedInUser>

    @POST("setLoggedInUser")
    suspend fun setLoggedInUser(loggedInUser: LoggedInUser)
}
