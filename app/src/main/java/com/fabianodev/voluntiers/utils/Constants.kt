package com.fabianodev.voluntiers.utils

import androidx.annotation.Keep

object Constants {
    @Keep
    const val API_SERVER_NAME = "https://firestore.googleapis.com/v1/"
    const val API_SERVER_AUTH = "https://identitytoolkit.googleapis.com/v1/"
    const val PROJECT = "projects/voluntiers-4a4ff"
    const val DATABASEID = "(default)"

    const val API_KEY = "AIzaSyATLSd5MUNBIBKc2GfeO3XZKJAE3n-2O2c"
    const val COLLECTION = "user"
    const val APPOINTMENTS = "appointments"
    const val CONNECTTIMEOUT = 30
    const val READTIMEOUT = 10
    const val DATABASE_DOCUMENTS = "$API_SERVER_NAME$PROJECT/databases/$DATABASEID/documents"
    const val INSTITUTIONS = "/Institutions"
    const val INSTITUTION_ID = "{id}"
    const val User = "Users"
    const val TENANTSID = "/{tenantId}/"
    const val TENANTS = "tenants"
    const val TENANTS_ID = "$TENANTS$TENANTSID"

    const val ACCOUNTS = "accounts"
    const val SIGN_IN_WITH_PASSSWORD = "signInWithPassword"


    object Endpoint {
        const val BASE_URL = API_SERVER_AUTH
        const val USER_COLLECTION = "$BASE_URL/$COLLECTION"
        const val POST_SIGN_IN_WITH_PASSSWORD = "$BASE_URL$ACCOUNTS:$SIGN_IN_WITH_PASSSWORD?key=$API_KEY"
        const val GET_APPOINTMENTS = "$DATABASE_DOCUMENTS/$APPOINTMENTS"
        const val GET_TASKS = "$DATABASE_DOCUMENTS$INSTITUTIONS/$INSTITUTION_ID/tasks/"

        /** Accounts **/
        const val POST_CREATE_AUTH_URI = "$API_SERVER_AUTH/accounts:createAuthUri"
        const val POST_DELETE = "$API_SERVER_AUTH$ACCOUNTS:delete"
        const val POST_ISSUES_AML_RESPONSE = "$API_SERVER_AUTH$ACCOUNTS:issueSamlResponse"
        const val POST_LOOKUP = "$API_SERVER_AUTH$ACCOUNTS:lookup"
        const val POST_RESET_PASSWORD = "$API_SERVER_AUTH$ACCOUNTS:resetPassword"
        const val POST_SEND_OOB_CODE = "$API_SERVER_AUTH$ACCOUNTS:sendOobCode"
        const val POST_SEND_VERIFICATION_CODE = "$API_SERVER_AUTH$ACCOUNTS:sendVerificationCode"
        const val POST_SIGN_IN_WITH_CUSTOM_TOKEN = "$API_SERVER_AUTH$ACCOUNTS:signInWithCustomToken"
        const val POST_SIGN_IN_WITH_EMAIL_LINK = "$API_SERVER_AUTH$ACCOUNTS:signInWithEmailLink"
        const val POST_SIGN_IN_WITH_GAME_CENTER = "$API_SERVER_AUTH$ACCOUNTS:signInWithGameCenter"
        const val POST_SIGN_IN_WITH_ID = "$API_SERVER_AUTH$ACCOUNTS:signInWithIdp"
        const val POST_SIGN_IN_WITH_PASSWORD = "$API_SERVER_AUTH$ACCOUNTS:signInWithPassword?key=$API_KEY"
        const val POST_SIGN_IN_WITH_PHONE_NUMBER = "$API_SERVER_AUTH$ACCOUNTS:signInWithPhoneNumber"
        const val POST_SIGN_UP = "$API_SERVER_AUTH$ACCOUNTS:signUp"
        const val POST_UPDATE = "$API_SERVER_AUTH$ACCOUNTS:update"
        const val POST_VERIFY_IOS_CLIENT = "$API_SERVER_AUTH$ACCOUNTS:verifyIosClient"

        /** Projects **/
        const val POST_PROJECTS_ACCOUNTS = "$API_SERVER_AUTH$PROJECT$ACCOUNTS"
        const val POST_PROJECTS_CREATE_SESSION_COOKIE = "$API_SERVER_AUTH$PROJECT:createSessionCookie"
        const val POST_PROJECTS_QUERY_ACCOUNTS = "$API_SERVER_AUTH$PROJECT:queryAccounts"

        /** projects.accounts **/
        const val POST_PROJECTS_ACCOUNTS_BATCHCREATE = "$API_SERVER_AUTH$PROJECT/$ACCOUNTS:batchCreate"
        const val POST_PROJECTS_ACCOUNTS_CREATE_SESSION_COOKIE = "$API_SERVER_AUTH$PROJECT:createSessionCookie"
        const val POST_PROJECTS_ACCOUNTS_BATCH_DELETE = "$API_SERVER_AUTH$PROJECT$ACCOUNTS:batchDelete"
        const val GET_PROJECTS_ACCOUNTS_BATCHGET = "$API_SERVER_AUTH$PROJECT$ACCOUNTS:batchGet"
        const val POST_PROJECTS_ACCOUNTS_DELETE = "$API_SERVER_AUTH$PROJECT}$ACCOUNTS:delete"
        const val POST_PROJECTS_ACCOUNTS_LOOKUP = "$API_SERVER_AUTH$PROJECT$ACCOUNTS:lookup"
        const val POST_PROJECTS_ACCOUNTS_QUERY = "$API_SERVER_AUTH$PROJECT$ACCOUNTS:query"
        const val POST_PROJECTS_ACCOUNTS_SEND_OOB_CODE = "$API_SERVER_AUTH$PROJECT$ACCOUNTS:sendOobCode"
        const val POST_PROJECTS_ACCOUNTS_UPDATE = "$API_SERVER_AUTH$PROJECT$ACCOUNTS:update"

        /** projects.tenants **/
        const val POST_PROJECTS_TENANTS_ACCOUNTS = "$API_SERVER_AUTH$PROJECT$TENANTS_ID$ACCOUNTS"
        const val POST_PROJECTS_TENANTS_CREATE_SESSION_COOKIE = "$API_SERVER_AUTH$PROJECT$TENANTS_ID$ACCOUNTS}:createSessionCookie"

        /** projects.tenants.accounts **/
        const val POST_PROJECTS_TENANTS_BATCHCREATE = "$API_SERVER_AUTH/projects/{targetProjectId}/tenants/{tenantId}$ACCOUNTS:batchCreate"
        const val POST_PROJECTS_TENANTS_BATCHDELETE = "$API_SERVER_AUTH/projects/{targetProjectId}/tenants/{tenantId}$ACCOUNTS:batchDelete"
        const val GET_PROJECTS_TENANTS_BATCHGET = "$API_SERVER_AUTH/projects/{targetProjectId}/tenants/{tenantId}$ACCOUNTS:batchGet"
        const val POST_PROJECTS_TENANTS_DELETE = "$API_SERVER_AUTH/projects/{targetProjectId}/tenants/{tenantId}$ACCOUNTS:delete"
        const val POST_PROJECTS_TENANTS_LOOKUP = "$API_SERVER_AUTH/projects/{targetProjectId}/tenants/{tenantId}$ACCOUNTS:lookup"
        const val POST_PROJECTS_TENANTS_QUERY = "$API_SERVER_AUTH/projects/{targetProjectId}/tenants/{tenantId}$ACCOUNTS:query"
        const val POST_PROJECTS_TENANTS_SENDOOBCODE = "$API_SERVER_AUTH/projects/{targetProjectId}/tenants/{tenantId}$ACCOUNTS:sendOobCode"
        const val POST_PROJECTS_TENANTS_UPDATE = "$API_SERVER_AUTH/projects/{targetProjectId}/tenants/{tenantId}$ACCOUNTS:update"
    }
}
