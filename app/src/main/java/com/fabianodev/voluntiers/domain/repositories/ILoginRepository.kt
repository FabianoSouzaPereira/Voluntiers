package com.fabianodev.voluntiers.domain.repositories

import com.fabianodev.voluntiers.data.login.DataResult
import com.fabianodev.voluntiers.domain.model.LoggedInUser


/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

interface ILoginRepository {
    var user: LoggedInUser?
    val isLoggedIn: Boolean
    fun logout()
    fun login(username: String, password: String): DataResult<LoggedInUser>
    fun setLoggedInUser(loggedInUser: LoggedInUser)
}