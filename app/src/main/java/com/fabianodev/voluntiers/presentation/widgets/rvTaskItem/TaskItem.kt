package com.fabianodev.voluntiers.presentation.widgets.rvTaskItem

import android.widget.Button

data class TaskItem(
        val title: String,
        val description: String,
        val btnSave: Button?,
        val btnDelete: Button?
)

