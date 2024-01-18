package com.fabianodev.voluntiers.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fabianodev.voluntiers.presentation.widgets.rvTaskItem.TaskItem
import com.fabianodev.voluntiers.presentation.widgets.rvTaskItem.TaskItemView

class HomeAdapter(val items: MutableList<TaskItem>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val taskItemView = TaskItemView(parent.context)
        return ViewHolder(taskItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.taskItemView.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: TaskItem, position: Int) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun saveItem(item: TaskItem, position: Int) {
        notifyItemChanged(position)
    }

    fun update(list: MutableList<TaskItem>, position: Int) {
        if (position >= 0 && position < items.size) {
            items.removeAt(position)
            items.addAll(position, list)
            notifyItemRemoved(position)
            notifyItemRangeInserted(position, list.size)
        }
    }

    inner class ViewHolder(val taskItemView: TaskItemView) : RecyclerView.ViewHolder(taskItemView)
}
