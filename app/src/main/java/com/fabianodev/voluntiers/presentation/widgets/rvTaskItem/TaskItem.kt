package com.fabianodev.voluntiers.presentation.widgets.rvTaskItem

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskItem(
        @SerializedName("name")
        val taskId: String,
        @SerializedName("fields")
        val fields: TaskFields,
        @SerializedName("createTime")
        val createTime: String,
        @SerializedName("updateTime")
        val updateTime: String
) : Parcelable

@Parcelize
data class TaskFields(
        @SerializedName("codigo")
        val codigo: FirestoreValue,
        @SerializedName("title")
        val title: FirestoreValue,
        @SerializedName("description")
        val description: FirestoreValue,
        @SerializedName("date")
        val date: FirestoreValue,
        @SerializedName("status")
        val status: FirestoreValue,
        @SerializedName("executor")
        val executor: FirestoreValue,
) : Parcelable

@Parcelize
data class FirestoreValue(
        @SerializedName("stringValue")
        val value: String
) : Parcelable

enum class TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}

