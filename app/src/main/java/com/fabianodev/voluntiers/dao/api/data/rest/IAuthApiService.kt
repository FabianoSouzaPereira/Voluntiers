package com.fabianodev.voluntiers.dao.api.data.rest

import com.fabianodev.voluntiers.dao.entities.login.LoginRequest
import com.fabianodev.voluntiers.dao.entities.login.LogoutRequest
import com.fabianodev.voluntiers.data.login.DataResult
import com.fabianodev.voluntiers.domain.model.User
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.LoggedInUser
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.SignUpWithPassword
import com.fabianodev.voluntiers.utils.Constants.Endpoint.POST_LOGIN
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthApiService {

    @POST("logout/")
    suspend fun logout(@Body logoutRequest: LogoutRequest): User

    @POST(POST_LOGIN)
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): SignUpWithPassword.SignUpResponse

    @POST("logged/")
    suspend fun setLoggedInUser(loggedInUser: LoggedInUser): DataResult<LoggedInUser>
}
