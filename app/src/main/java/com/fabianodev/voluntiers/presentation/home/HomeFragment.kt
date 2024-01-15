package com.fabianodev.voluntiers.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fabianodev.voluntiers.MainActivity
import com.fabianodev.voluntiers.databinding.FragmentHomeBinding
import com.fabianodev.voluntiers.presentation.widgets.rvTaskItem.TaskItem
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvTasksList: RecyclerView
    private lateinit var linearLayout: LinearLayoutCompat
    private val taskList = mutableListOf<TaskItem>()

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
        linearLayout.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )
        linearLayout.orientation = LinearLayoutCompat.VERTICAL
        rvTasksList = RecyclerView(requireContext())
        rvTasksList.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )

        taskList.add(TaskItem(title = "Tarefa 1", description = "Descrição da tarefa 1", btnSave = Button(context), btnDelete = Button(context)))
        taskList.add(TaskItem(title = "Tarefa 2", description = "Descrição da tarefa 2", btnSave = Button(context), btnDelete = Button(context)))
        taskList.add(TaskItem(title = "Tarefa 3", description = "Descrição da tarefa 3", btnSave = Button(context), btnDelete = Button(context)))

        val adapter = HomeAdapter(items = taskList)
        adapter.setHasStableIds(true)
        rvTasksList.adapter = adapter
        rvTasksList.layoutManager = LinearLayoutManager(requireContext())
        linearLayout.addView(rvTasksList)

        root.addView(linearLayout)

        return root
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}