package com.fabianodev.voluntiers.domain.model.home

import com.fabianodev.voluntiers.presentation.widgets.rvTaskItem.TaskItem

/**
 * Created by Fabiano Pereira on 20/01/2024.
 */
data class HomeView(val homeTaskList: List<TaskItem> = arrayListOf())