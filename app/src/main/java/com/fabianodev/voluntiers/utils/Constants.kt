package com.fabianodev.voluntiers.utils

import androidx.annotation.Keep

@Suppress("MemberVisibilityCanBePrivate")
object Constants {
    @Keep
    const val API_SERVER_NAME = "https://console.cloud.google.com/v1/"
    const val API_SERVER_AUTH = "https://identitytoolkit.googleapis.com/v1/"
    const val PROJECT = "project=voluntiers-4a4ff"
    const val ACCOUNTS = "accounts:signInWithPassword"
    const val API_KEY = "AIzaSyATLSd5MUNBIBKc2GfeO3XZKJAE3n-2O2c"
    const val COLECTION = "user"
    const val CONNECTTIMEOUT = 30
    const val READTIMEOUT = 10

    object Endpoint {
        const val BASE_URL = API_SERVER_AUTH
        const val USER_COLETION = "$BASE_URL/${COLECTION}"
        const val POST_LOGIN = "$BASE_URL$ACCOUNTS?key=$API_KEY"
    }
}