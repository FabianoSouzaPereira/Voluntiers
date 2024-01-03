package com.fabianodev.voluntiers.domain.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
        @SerializedName("id")
        val id: Long,
        @SerializedName("username")
        val username: String,
        @SerializedName("password")
        val password: String) : Parcelable
