package com.fabianodev.voluntiers.dao.entities.login

import com.google.gson.annotations.SerializedName

data class LoginRequestOAuth(
    @SerializedName("email")
    val id_token: String,
    @SerializedName("providerId")
    val providerId: String,
    @SerializedName("requestUri")
    val requestUri: String,
    @SerializedName("returnIdpCredential")
    val returnIdpCredential: Boolean,
    @SerializedName("returnSecureToken")
    val returnSecureToken: Boolean
)