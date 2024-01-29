package com.fabianodev.voluntiers.dao.entities.login.loginaccount

import com.google.gson.annotations.SerializedName


/**
 * Created by Fabiano Pereira on 29/01/2024.
 */
data class AccountDelete(
        @SerializedName("delegatedProjectNumber") val delegatedProjectNumber: String,
        @SerializedName("idToken") val idToken: String,
        @SerializedName("localId") val localId: String,
        @SerializedName("targetProjectId") val targetProjectId: String,
        @SerializedName("tenantId") val tenantId: String
)