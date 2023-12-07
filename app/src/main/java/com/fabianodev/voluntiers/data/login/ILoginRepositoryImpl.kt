package com.fabianodev.voluntiers.data.login

import android.content.Context
import com.fabianodev.voluntiers.domain.model.LoggedInUser
import com.fabianodev.voluntiers.domain.repositories.ILoginRepository
import javax.inject.Inject

class ILoginRepositoryImpl @Inject constructor(val context: Context, val dataSource: LoginDataSource ) : ILoginRepository {
    // in-memory cache of the loggedInUser object
    override var user: LoggedInUser? = null

    override val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null

    }

    override fun logout() {
        user = null
    }

    override fun login(username: String, password: String): DataResult<LoggedInUser> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is DataResult.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    override fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}