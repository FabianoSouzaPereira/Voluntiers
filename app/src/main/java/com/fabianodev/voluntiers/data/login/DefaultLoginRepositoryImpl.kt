package com.fabianodev.voluntiers.data.login

import android.content.Context
import com.fabianodev.voluntiers.domain.model.login.LoggedInUser
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import javax.inject.Inject

class DefaultLoginRepositoryImpl @Inject constructor(val context: Context, val dataSource: RemoteLoginDataSource) : LoginRepository {
    // in-memory cache of the loggedInUser object
    override var user: LoggedInUser? = null

    override val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null

    }

    override suspend fun logout() {
        user = null
    }

    override suspend fun login(username: String, password: String): DataResult<LoggedInUser> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is DataResult.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    override suspend fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}