package com.fabianodev.voluntiers.domain.model.user

data class UserResult(
        val success: UserView? = null,
        val error: Int? = null
)