package com.fabianodev.voluntiers.domain.model

/**
 * Data class that captures user information for logged in users retrieved from ILoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String
)