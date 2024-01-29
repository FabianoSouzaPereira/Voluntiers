package com.fabianodev.voluntiers.presentation.widgets.rvTaskItem

/**
 * Created by Fabiano Pereira on 15/01/2024.
 */
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.fabianodev.voluntiers.R

class TaskItemView(context: Context) : LinearLayout(context) {
    private val titleTextView: TextView
    private val descriptionTextView: TextView

    init {
        val states = arrayOf(
                intArrayOf(android.R.attr.state_pressed),
                intArrayOf(android.R.attr.state_focused),
                intArrayOf(android.R.attr.state_active),
                intArrayOf()
        )
        val colors = intArrayOf(
                Color.RED,
                Color.GREEN,
                Color.GREEN,
                Color.WHITE
        )
        val colorStateList = ColorStateList(states, colors)

        val backgroundDrawable = GradientDrawable()
        backgroundDrawable.setColor(Color.BLACK)
        backgroundDrawable.setStroke(2, Color.GRAY)
        backgroundDrawable.cornerRadius = 12f
        background = backgroundDrawable

        val paddingPx = resources.getDimensionPixelSize(R.dimen.task_item_padding)
        setPadding(paddingPx, paddingPx, paddingPx, paddingPx)
        val typeface = ResourcesCompat.getFont(context, R.font.roboto_regular)

        orientation = VERTICAL
        layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )

        titleTextView = TextView(context)
        titleTextView.typeface = typeface
        titleTextView.setTextColor(colorStateList)
        titleTextView.setPadding(0, 0, 0, 3)
        addView(titleTextView)

        descriptionTextView = TextView(context)
        descriptionTextView.typeface = typeface
        addView(descriptionTextView)

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (layoutParams as? MarginLayoutParams)?.let {
            val marginPx = resources.getDimensionPixelSize(R.dimen.task_item_margin)
            val marginPxLeft = resources.getDimensionPixelSize(R.dimen.task_item_margin_left)
            val marginPxRight = resources.getDimensionPixelSize(R.dimen.task_item_margin_right)
            it.setMargins(marginPxLeft, marginPx, marginPxRight, marginPx)
            layoutParams = it
        }
    }

    fun bind(taskItem: TaskItem) {
        titleTextView.text = taskItem.fields.title.toString()
        descriptionTextView.text = taskItem.fields.description.toString()
    }
}
