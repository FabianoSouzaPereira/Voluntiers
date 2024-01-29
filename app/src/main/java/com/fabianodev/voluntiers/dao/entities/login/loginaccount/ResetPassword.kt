package com.fabianodev.voluntiers.dao.entities.login.loginaccount

import com.google.gson.annotations.SerializedName


/**
 * Created by Fabiano Pereira on 29/01/2024.
 */
data class ResetPassword(
        @SerializedName("email") val email: String,
        @SerializedName("newPassword") val newPassword: String,
        @SerializedName("oldPassword") val oldPassword: String,
        @SerializedName("oobCode") val oobCode: String,
        @SerializedName("tenantId") val tenantId: String
)