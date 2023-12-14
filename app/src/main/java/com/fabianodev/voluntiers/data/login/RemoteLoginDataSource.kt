package com.fabianodev.voluntiers.data.login

import com.fabianodev.voluntiers.dao.api.data.rest.IAuthApiService
import com.fabianodev.voluntiers.dao.entities.login.LoginRequest
import com.fabianodev.voluntiers.dao.entities.login.LogoutRequest
import com.fabianodev.voluntiers.domain.model.User
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.LoggedInUser
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Class that handles authenticationmodel w/ login credentials and retrieves user information.
 */
class RemoteLoginDataSource @Inject constructor(private val apiService: IAuthApiService) : LoginRepository {
    override var user: User? = null
    override var isLoggedIn: Boolean = false
        get() = user != null

    override var loggedInUser: LoggedInUser? = null

    //   private val documentReference = firebaseFirestore.document("data")

    override suspend fun logout(username: String) {
        isLoggedIn = false
        user = null
        try {
            val logoutRequest = LogoutRequest(username)
            val user = apiService.logout(logoutRequest)

            if (user.id < 0.toLong()) {
                setLoggedInUser(LoggedInUser(user.id, user.username))
            }

        } catch (e: Throwable) {
            print(e.printStackTrace())
        }
    }

    override suspend fun login(username: String, password: String, returnSecureToken: Boolean): User? {
        return try {
            withContext(Dispatchers.IO) {
                val loginRequest = LoginRequest(username, password, returnSecureToken)
                val user = apiService.login(loginRequest)

                if (user.id < 0.toLong()) {
                    setLoggedInUser(LoggedInUser(user.id, user.username))
                }
                user
            }
        } catch (e: Throwable) {
            throw e
        }
    }


    override suspend fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.loggedInUser = loggedInUser
    }

}