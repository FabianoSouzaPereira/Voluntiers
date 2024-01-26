package com.fabianodev.voluntiers.presentation.home

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fabianodev.voluntiers.MainActivity
import com.fabianodev.voluntiers.R
import com.fabianodev.voluntiers.databinding.FragmentHomeBinding
import com.fabianodev.voluntiers.presentation.home.widget.HomeShimmer
import com.fabianodev.voluntiers.presentation.widgets.DrawableDivider
import com.fabianodev.voluntiers.presentation.widgets.rvTaskItem.CustomImageViewWithText
import com.fabianodev.voluntiers.presentation.widgets.rvTaskItem.TaskItem
import com.fabianodev.voluntiers.ui.swipe.SwipeToDeleteCallback
import com.fabianodev.voluntiers.ui.swipe.SwipeToSaveCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
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
    private lateinit var marginLayoutParamCompat: MarginLayoutParamsCompat
    private lateinit var layoutParams: ConstraintLayout.LayoutParams
    private lateinit var mAdapter: HomeAdapter
    private val taskList = mutableListOf<TaskItem>()
    private val newTaskList = mutableListOf<TaskItem>()
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

        linearLayout = LinearLayoutCompat(requireContext()).apply {
            id = ViewCompat.generateViewId()
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT
            ).apply {
                topMargin = resources.getDimensionPixelSize(R.dimen.toolbar_height)
            }
        }

        linearLayout = LinearLayoutCompat(requireContext()).apply {
            id = ViewCompat.generateViewId()
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT
            ).apply {
                topMargin = resources.getDimensionPixelSize(R.dimen.toolbar_height)
            }
        }

        linearLayout = LinearLayoutCompat(requireContext()).apply {
            id = ViewCompat.generateViewId()
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT
            ).apply {
                topMargin = resources.getDimensionPixelSize(R.dimen.toolbar_height)
            }
        }
        val shimmerLayouts = mutableListOf<View>()
        for (i in 1..16) {
            val type = i % 2 != 0 // Alternando entre true e false
            shimmerLayouts.add(HomeShimmer().shimmer(requireContext(), type))
            if (!type) {
                shimmerLayouts.add(DrawableDivider().createDivider(requireContext()))
            }
        }
        shimmerLayouts.forEach { shimmerLayout ->
            linearLayout.addView(shimmerLayout)
        }

        layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.toolbar_height)
        rvTasksList = RecyclerView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            )
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.homeResult.observe(viewLifecycleOwner,
                Observer { homeResult ->
                    //   shimmerLayout.stopShimmer()
                    //   shimmerLayout.visibility = View.GONE
                    if (homeResult == null)
                        return@Observer

                    homeResult.success?.let {
                        val list = mutableListOf<TaskItem>()
                        list.add(TaskItem(codigo = 1, title = "Tarefa 1", description = "Descrição da tarefa 1"))
                        list.add(TaskItem(codigo = 2, title = "Tarefa 2", description = "Descrição da tarefa 2"))
                        list.add(TaskItem(codigo = 3, title = "Tarefa 3", description = "Descrição da tarefa 3"))
                        taskList.addAll(list)
                        shimmerLayouts.forEach { shimmerLayout ->
                            linearLayout.removeView(shimmerLayout)
                            linearLayout.removeView(DrawableDivider().createDivider(requireContext()))
                        }

                        for ((index, item) in taskList.withIndex()) {
                            mAdapter.update(list = taskList, position = index)
                        }
                        if (taskList.size > 0) {
                            shimmerLayouts.forEach { shimmerLayout ->
                                linearLayout.removeView(shimmerLayout)
                                linearLayout.removeView(DrawableDivider().createDivider(requireContext()))
                            }
                            linearLayout.addView(rvTasksList)
                        } else {
                            val customImageView = CustomImageViewWithText(requireContext(), null)
                            customImageView.setImageResource(R.drawable.empty_list)
                            customImageView.setText(getString(R.string.nothing_to_show))

                            linearLayout.addView(customImageView)
                        }

                    }

                    /*   homeResult.error?.let {
                           val list = mutableListOf<TaskItem>()
                           list.add(TaskItem(codigo = 1, title = "Sem dados", description = "Erro ao carregar o conteúdo"))
                           taskList.addAll(list)
                           linearLayout.removeView(shimmerLayout)

                           for ((index, item) in taskList.withIndex()) {
                               newTaskList.add(index, item)
                           }
                           mAdapter.updateALL(list = newTaskList)
                           if (newTaskList.size > 0) {
                               //  linearLayout.removeView(rvTasksList)
                               //   linearLayout.addView(rvTasksList)
                           } else {
                               val customImageView = CustomImageViewWithText(requireContext(), null)
                               customImageView.setImageResource(R.drawable.empty_list)
                               customImageView.setText(getString(R.string.nothing_to_show))

                               linearLayout.addView(customImageView)
                           }
                       } */
                }
        )
        mAdapter = HomeAdapter(items = taskList)
        mAdapter.setHasStableIds(false)
        rvTasksList.adapter = mAdapter
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
                mAdapter.removeItem(position)
                val snackbar = Snackbar.make(linearLayout, getString(R.string.o_item_foi_salvo), Snackbar.LENGTH_LONG)
                snackbar.setAction(getString(R.string.cancel)) {
                    mItemReturned = true
                    mAdapter.restoreItem(item, position)
                    rvTasksList.scrollToPosition(position)
                    snackbar.dismiss()
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
                Timer().schedule(3000) {
                    requireActivity().runOnUiThread {
                        if (!mItemReturned) {
                            mAdapter.notifyItemChanged(position)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.getHomeContent(id = "HVsHmyi8zJP39LQbB6sH")
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}