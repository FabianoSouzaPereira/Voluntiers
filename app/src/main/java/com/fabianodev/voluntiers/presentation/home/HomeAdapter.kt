package com.fabianodev.voluntiers.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fabianodev.voluntiers.presentation.widgets.rvTaskItem.TaskItem
import com.fabianodev.voluntiers.presentation.widgets.rvTaskItem.TaskItemView

class HomeAdapter(private val items: MutableList<TaskItem>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var mListItems: MutableList<TaskItem> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val taskItemView = TaskItemView(parent.context)
        return ViewHolder(taskItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.taskItemView.bind(item)

        holder.taskItemView.btnSave.setOnClickListener {
            val savedItem = items.removeAt(position)
            notifyItemRemoved(position)
        }

        holder.taskItemView.btnDelete.setOnClickListener {
            val deletedItem = items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(list: List<TaskItem>) {
        mListItems.clear()
        mListItems.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val taskItemView: TaskItemView) : RecyclerView.ViewHolder(taskItemView)
}
