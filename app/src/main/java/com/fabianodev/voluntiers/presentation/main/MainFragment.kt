package com.fabianodev.voluntiers.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fabianodev.voluntiers.MainActivity
import com.fabianodev.voluntiers.R
import com.fabianodev.voluntiers.databinding.FragmentMainBinding
import com.fabianodev.voluntiers.utils.PreferenceManager


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var token: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
        preferenceManager = PreferenceManager(requireContext())
        token = preferenceManager.getPreferenceString("token", "")
        if (token.isEmpty()) {
            findNavController().navigate(R.id.loginFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("TEST $token")
        if (token.isNotEmpty()) {
            findNavController().navigate(R.id.homeFragment)
        }
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