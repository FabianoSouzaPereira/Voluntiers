package com.fabianodev.voluntiers.presentation.widgets.rvTaskItem

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskItem(
        @SerializedName("codigo")
        val codigo: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
) : Parcelable

