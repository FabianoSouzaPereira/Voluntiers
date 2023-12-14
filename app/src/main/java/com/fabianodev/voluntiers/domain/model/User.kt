package com.fabianodev.voluntiers.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(val id: Long, val username: String, val password: String) : Parcelable
