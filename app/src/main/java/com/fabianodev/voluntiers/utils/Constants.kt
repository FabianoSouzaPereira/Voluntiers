package com.fabianodev.voluntiers.utils

import androidx.annotation.Keep

object Constants {
    @Keep
    const val API_SERVER_NAME = "https://firestore.googleapis.com/v1/"
    const val API_SERVER_AUTH = "https://identitytoolkit.googleapis.com/v1/"
    const val PROJECT = "project=voluntiers-4a4ff"
    const val DATABASEID = "default"
    const val ACCOUNTS = "accounts:signInWithPassword"
    const val API_KEY = "AIzaSyATLSd5MUNBIBKc2GfeO3XZKJAE3n-2O2c"
    const val COLLECTION = "user"
    const val APPOINTMENTS = "appointments"
    const val CONNECTTIMEOUT = 30
    const val READTIMEOUT = 10
    const val DATABASE_DOCUMENTS = "$API_SERVER_NAME$PROJECT/databases/$DATABASEID/documents/"
    const val INSTITUTIONS = "/Institutions/"
    const val INSTITUTION_ID = "id"
    const val User = "Users"

    object Endpoint {
        const val BASE_URL = API_SERVER_AUTH
        const val USER_COLLECTION = "$BASE_URL/$COLLECTION"
        const val POST_LOGIN = "$BASE_URL$ACCOUNTS?key=$API_KEY"
        const val GET_APPOINTMENTS = "$DATABASE_DOCUMENTS/$APPOINTMENTS"
        const val GET_TASKS = "$DATABASE_DOCUMENTS$INSTITUTIONS$INSTITUTION_ID"
    }
}
