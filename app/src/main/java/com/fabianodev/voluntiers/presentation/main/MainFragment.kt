package com.fabianodev.voluntiers.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.fabianodev.voluntiers.AppApplication
import com.fabianodev.voluntiers.databinding.FragmentMainBinding
import com.fabianodev.voluntiers.di.MainComponent


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var mainComponent: MainComponent
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent =
            (requireActivity().applicationContext as AppApplication).appComponent.mainComponent().create()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}