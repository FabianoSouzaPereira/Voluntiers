package com.fabianodev.voluntiers.data.login

import com.fabianodev.voluntiers.dao.api.data.rest.ILoginApiService
import com.fabianodev.voluntiers.dao.entities.LoginRequest
import com.fabianodev.voluntiers.domain.model.login.LoggedInUser
import com.fabianodev.voluntiers.domain.repositories.LoginRepository
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
        // Lógica de autenticação e chamada para o RemoteLoginDataSource
        val result = loginApiService.login(LoginRequest(username, password))

        // Tradução do resultado para um formato de domínio, se necessário

        return result
    }

    override suspend fun setLoggedInUser(loggedInUser: LoggedInUser) {
        TODO("Not yet implemented")
    }

}