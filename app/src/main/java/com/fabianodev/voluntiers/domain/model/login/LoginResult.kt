package com.fabianodev.voluntiers.domain.model.login

/**
 * Authentication result : success (user details) or authenticationmodel message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)