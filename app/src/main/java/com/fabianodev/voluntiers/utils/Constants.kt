package com.fabianodev.voluntiers.utils

import androidx.annotation.Keep

@Suppress("MemberVisibilityCanBePrivate")
object Constants {
    @Keep
    const val API_SERVER_NAME = "https://console.cloud.google.com/"
    const val PROJECT = "project=voluntiers-4a4ff"
    const val API_KEY = ""
    const val COLECTION = "user"
    const val CONNECTTIMEOUT = 30
    const val READTIMEOUT = 10

    object Endpoint {
        const val BASE_URL = "$API_SERVER_NAME"
        const val USER_COLETION = "$BASE_URL/${COLECTION}"
        const val POST_LOGIN = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=${API_KEY}"
    }
}