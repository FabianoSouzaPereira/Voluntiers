package com.fabianodev.voluntiers.utils

import android.util.Patterns
import javax.inject.Inject

class EmailValidator @Inject constructor() {
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isNoBlank(email: String): Boolean {
        return email.isNotBlank()
    }
}