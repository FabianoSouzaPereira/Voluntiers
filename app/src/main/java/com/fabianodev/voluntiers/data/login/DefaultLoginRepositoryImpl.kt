package com.fabianodev.voluntiers.data.login

import android.content.Context
import com.fabianodev.voluntiers.domain.model.User
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.LoggedInUser
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.SignUpWithPassword
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import javax.inject.Inject

class DefaultLoginRepositoryImpl @Inject constructor(val context: Context, private val dataSource: RemoteLoginDataSource) : LoginRepository {
    /* in-memory cache of the loggedInUser object */
    override var user: User?
    override var loggedInUser: LoggedInUser?

    override val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
        loggedInUser = null
    }

    override suspend fun logout(username: String) {
        user = null
        return dataSource.logout(username)
    }

    override suspend fun login(username: String, password: String, returnSecureToken: Boolean): SignUpWithPassword.SignUpResponse {

        try {
            val result = dataSource.login(username, password, returnSecureToken)

            if (result.email.isNotEmpty()) {
                setLoggedInUser(LoggedInUser(result.email, result.displayName))
                return result
            }

            return result
        } catch (e: Exception) {
            throw e
        }

    }

    override suspend fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.loggedInUser = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}