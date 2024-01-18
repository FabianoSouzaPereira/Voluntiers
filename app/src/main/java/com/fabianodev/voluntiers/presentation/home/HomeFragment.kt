package com.fabianodev.voluntiers.presentation.home

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fabianodev.voluntiers.MainActivity
import com.fabianodev.voluntiers.R
import com.fabianodev.voluntiers.databinding.FragmentHomeBinding
import com.fabianodev.voluntiers.presentation.widgets.rvTaskItem.TaskItem
import com.fabianodev.voluntiers.ui.SwipeToDeleteCallback
import com.fabianodev.voluntiers.ui.SwipeToSaveCallback
import com.google.android.material.snackbar.Snackbar
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvTasksList: RecyclerView
    private lateinit var linearLayout: LinearLayoutCompat
    private lateinit var layoutParams: ConstraintLayout.LayoutParams
    private lateinit var mAdapter: HomeAdapter
    private val taskList = mutableListOf<TaskItem>()
    private var mItemPressed: Boolean = false
    private var mItemReturned: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root
        linearLayout = LinearLayoutCompat(requireContext())
        linearLayout.id = ViewCompat.generateViewId()
        linearLayout.orientation = LinearLayoutCompat.VERTICAL
        linearLayout.orientation = LinearLayoutCompat.VERTICAL
        layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.toolbar_height)

        rvTasksList = RecyclerView(requireContext())
        rvTasksList.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )
        val list = mutableListOf<TaskItem>()
        list.add(TaskItem(codigo = 1, title = "Tarefa 1", description = "Descrição da tarefa 1"))
        list.add(TaskItem(codigo = 2, title = "Tarefa 2", description = "Descrição da tarefa 2"))
        list.add(TaskItem(codigo = 3, title = "Tarefa 3", description = "Descrição da tarefa 3"))
        taskList.addAll(list)


        mAdapter = HomeAdapter(items = taskList)
        mAdapter.setHasStableIds(false)
        rvTasksList.adapter = mAdapter
        rvTasksList.layoutManager = LinearLayoutManager(requireContext())
        linearLayout.addView(rvTasksList)

        root.addView(linearLayout, layoutParams)
        enableSwipeToAndUndoAndSave()
        enableSwipeToDeleteAndUndo()

        return root
    }

    /* Save item from Recycle view and undo if until 3 seconds */
    private fun enableSwipeToAndUndoAndSave() {
        val swipeToSaveCallback = object : SwipeToSaveCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = mAdapter.items[position]
                mAdapter.saveItem(item, position)
                // Implementar a lógica de salvar o item aqui
                // Exemplo: Chamar mAdapter.saveItem(item, position)

                Timer().schedule(3000) {
                    requireActivity().runOnUiThread {
                        if (!mItemReturned) {
                            mAdapter.update(taskList, position)
                            mItemReturned = false
                        }
                    }
                }
            }
        }
        val itemTouchhelperSave = ItemTouchHelper(swipeToSaveCallback)
        itemTouchhelperSave.attachToRecyclerView(rvTasksList)
    }


    /* Delete item from Recycle view and undo if until 3 seconds */
    private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                val item = mAdapter.items[position]
                mAdapter.removeItem(position)
                val snackbar = Snackbar.make(linearLayout, "O item foi removido da lista.", Snackbar.LENGTH_LONG)
                snackbar.setAction("Cancelar") {
                    mItemReturned = true
                    mAdapter.restoreItem(item, position)
                    rvTasksList.scrollToPosition(position)
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
                Timer().schedule(3000) {
                    requireActivity().runOnUiThread {
                        if (!mItemReturned) {
                            mAdapter.update(taskList, position)
                            mItemReturned = false
                        }
                    }
                }
                mAdapter.update(taskList, position)
            }
        }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(rvTasksList)
    }


    companion object {
        fun newInstance() = HomeFragment()
    }
}