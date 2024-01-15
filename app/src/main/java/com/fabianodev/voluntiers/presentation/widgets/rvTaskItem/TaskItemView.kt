package com.fabianodev.voluntiers.presentation.widgets.rvTaskItem

/**
 * Created by Fabiano Pereira on 15/01/2024.
 */
import android.content.Context
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class TaskItemView(context: Context) : LinearLayout(context) {
    private val titleTextView: TextView
    private val descriptionTextView: TextView
    val btnDelete: Button
    val btnSave: Button

    init {
        orientation = VERTICAL
        layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )

        titleTextView = TextView(context)
        titleTextView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        addView(titleTextView)

        descriptionTextView = TextView(context)
        descriptionTextView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        addView(descriptionTextView)

        btnSave = Button(context)
        btnSave.text = "Salvar"
        addView(btnSave)

        btnDelete = Button(context)
        btnDelete.text = "Excluir"
        addView(btnDelete)
    }

    fun bind(taskItem: TaskItem) {
        titleTextView.text = taskItem.title
        descriptionTextView.text = taskItem.description
    }
}
