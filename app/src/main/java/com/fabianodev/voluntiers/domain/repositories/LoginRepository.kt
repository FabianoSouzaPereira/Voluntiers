package com.fabianodev.voluntiers.domain.repositories

import com.fabianodev.voluntiers.data.login.DataResult
import com.fabianodev.voluntiers.domain.model.login.LoggedInUser


/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

interface LoginRepository {
    var user: LoggedInUser?
    val isLoggedIn: Boolean
    suspend fun logout()
    suspend fun login(username: String, password: String): DataResult<LoggedInUser>
    suspend fun setLoggedInUser(loggedInUser: LoggedInUser)
}