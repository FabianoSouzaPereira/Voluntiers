package com.fabianodev.voluntiers.repository

import android.content.Context
import android.util.Log
import javax.inject.Inject

class DefaultRepository @Inject constructor(val context: Context) : Repository {
    override fun login() {
        Log.d("DefaultRepository", "Login Done")
    }
}