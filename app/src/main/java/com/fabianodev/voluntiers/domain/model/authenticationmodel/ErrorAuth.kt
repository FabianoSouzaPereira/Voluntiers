package com.fabianodev.voluntiers.domain.model.authenticationmodel

import com.google.gson.annotations.SerializedName

data class ErrorAuth(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?
)
