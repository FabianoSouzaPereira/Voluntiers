package com.fabianodev.voluntiers.dao.entities.login.loginaccount

import com.google.gson.annotations.SerializedName

/**
 * Created by Fabiano Pereira on 29/01/2024.
 */
data class SignUp(
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String,
        @SerializedName("displayName") val displayName: String,
        @SerializedName("captchaChallenge") val captchaChallenge: String,
        @SerializedName("captchaResponse") val captchaResponse: String,
        @SerializedName("instanceId") val instanceId: String,
        @SerializedName("idToken") val idToken: String,
        @SerializedName("emailVerified") val emailVerified: Boolean,
        @SerializedName("photoUrl") val photoUrl: String,
        @SerializedName("disabled") val disabled: Boolean,
        @SerializedName("localId") val localId: String,
        @SerializedName("phoneNumber") val phoneNumber: String,
        @SerializedName("tenantId") val tenantId: String,
        @SerializedName("targetProjectId") val targetProjectId: String,
        @SerializedName("mfaInfo") val mfaInfo: List<MfaFactor>,
        @SerializedName("clientType") val clientType: ClientType,
        @SerializedName("recaptchaVersion") val recaptchaVersion: RecaptchaVersion
)

data class MfaFactor(
        @SerializedName("factorType") val factorType: String,
        @SerializedName("factorId") val factorId: String
)

enum class ClientType {
    DESKTOP,
    MOBILE,
    WEB
}

enum class RecaptchaVersion {
    V2_CHECKBOX,
    V2_INVISIBLE,
    V3
}