package com.fabianodev.voluntiers.domain.model.login

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    var userId: Long,
    var displayName: String
)