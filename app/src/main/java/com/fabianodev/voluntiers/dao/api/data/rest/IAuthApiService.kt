package com.fabianodev.voluntiers.dao.api.data.rest

import com.fabianodev.voluntiers.dao.entities.login.LoginRequest
import com.fabianodev.voluntiers.dao.entities.login.LogoutRequest
import com.fabianodev.voluntiers.dao.entities.login.loginaccount.LoginAuthRequest
import com.fabianodev.voluntiers.data.login.DataResult
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.LoggedInUser
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.SignUpWithPassword
import com.fabianodev.voluntiers.utils.Constants
import com.fabianodev.voluntiers.utils.Constants.Endpoint.POST_SIGN_IN_WITH_PASSWORD
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthApiService {
    @POST(Constants.Endpoint.POST_CREATE_AUTH_URI)
    suspend fun create_auth_uri(
            @Body loginAuthRequest: LoginAuthRequest
    )

    @POST("logout/")
    suspend fun logout(@Body logoutRequest: LogoutRequest)

    @POST(POST_SIGN_IN_WITH_PASSWORD)
    suspend fun login(
            @Body loginRequest: LoginRequest
    ): SignUpWithPassword.SignUpResponse

    @POST("logged/")
    suspend fun setLoggedInUser(loggedInUser: LoggedInUser): DataResult<LoggedInUser>
}
