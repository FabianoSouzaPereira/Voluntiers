package com.fabianodev.voluntiers.dao.entities.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName(" returnSecureToken")
    val returnSecureToken: Boolean
)
