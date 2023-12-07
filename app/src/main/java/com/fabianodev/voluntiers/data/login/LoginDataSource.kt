package com.fabianodev.voluntiers.data.login

import com.fabianodev.voluntiers.domain.model.LoggedInUser
import java.io.IOException
import java.util.UUID
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource @Inject constructor() {

    fun login(username: String, password: String): DataResult<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Jane Doe")
            return DataResult.Success(fakeUser)
        } catch (e: Throwable) {
            return DataResult.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}