package com.fabianodev.voluntiers.domain.model.home

import android.os.Parcelable
import com.fabianodev.voluntiers.presentation.widgets.rvTaskItem.TaskItem
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Fabiano Pereira on 20/01/2024.
 */
@Parcelize
data class Home(
        @SerializedName("taskItem")
        val taskItem: List<TaskItem> = mutableListOf()
) : Parcelable