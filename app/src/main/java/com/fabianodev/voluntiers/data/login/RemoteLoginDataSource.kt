package com.fabianodev.voluntiers.data.login

import com.fabianodev.voluntiers.dao.api.data.rest.ILoginApiService
import com.fabianodev.voluntiers.domain.model.login.LoggedInUser
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import java.io.IOException
import java.util.UUID
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class RemoteLoginDataSource @Inject constructor(private val loginApiService: ILoginApiService) : LoginRepository {
    override var user: LoggedInUser?
        get() = TODO("Not yet implemented")
        set(value) {}
    override val isLoggedIn: Boolean
        get() = TODO("Not yet implemented")

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun login(username: String, password: String): DataResult<LoggedInUser> {
        return try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Jane Doe")
            DataResult.Success(fakeUser)
        } catch (e: Throwable) {
            DataResult.Error(IOException("Error logging in", e))
        }
    }

    override suspend fun setLoggedInUser(loggedInUser: LoggedInUser) {
        TODO("Not yet implemented")
    }

}