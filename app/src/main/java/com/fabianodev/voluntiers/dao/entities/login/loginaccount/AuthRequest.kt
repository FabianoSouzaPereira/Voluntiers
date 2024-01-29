package com.fabianodev.voluntiers.dao.entities.login.loginaccount

import com.google.gson.annotations.SerializedName

object AuthRequest {
    data class LoginAuthRequest(
            @SerializedName("appId") val appId: String,
            @SerializedName("authFlowType") val authFlowType: String,
            @SerializedName("context") val context: String,
            @SerializedName("continueUri") val continueUri: String,
            @SerializedName("customParameter") val customParameter: CustomParameter,
            @SerializedName("hostedDomain") val hostedDomain: String,
            @SerializedName("identifier") val identifier: String,
            @SerializedName("oauthConsumerKey") val oauthConsumerKey: String,
            @SerializedName("oauthScope") val oauthScope: String,
            @SerializedName("openidRealm") val openidRealm: String,
            @SerializedName("otaApp") val otaApp: String,
            @SerializedName("providerId") val providerId: String,
            @SerializedName("sessionId") val sessionId: String,
            @SerializedName("tenantId") val tenantId: String
    )

    data class CustomParameter(
            @SerializedName("string") val string: String
    )

    data class LoginAuthResponse(
            @SerializedName("allProviders") val allProviders: List<String>,
            @SerializedName("authUri") val authUri: String,
            @SerializedName("captchaRequired") val captchaRequired: String,
            @SerializedName("forExistingProvider") val forExistingProvider: String,
            @SerializedName("kind") val kind: String,
            @SerializedName("providerId") val providerId: String,
            @SerializedName("registered") val registered: String,
            @SerializedName("sessionId") val sessionId: String,
            @SerializedName("signinMethods") val signinMethods: List<String>
    )
}