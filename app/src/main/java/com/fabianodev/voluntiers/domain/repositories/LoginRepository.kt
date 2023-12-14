package com.fabianodev.voluntiers.domain.repositories

import com.fabianodev.voluntiers.domain.model.User
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.LoggedInUser


/**
 * Class that requests authenticationmodel and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

interface LoginRepository {
    var user: User?
    var loggedInUser: LoggedInUser?
    var isLoggedIn: Boolean
    suspend fun logout(username: String)
    suspend fun login(username: String, password: String, returnSecureToken: Boolean = true): User?
    suspend fun setLoggedInUser(loggedInUser: LoggedInUser)
}