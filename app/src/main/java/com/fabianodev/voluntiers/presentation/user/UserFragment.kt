package com.fabianodev.voluntiers.presentation.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fabianodev.voluntiers.MainActivity
import com.fabianodev.voluntiers.R
import javax.inject.Inject

class UserFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<UserViewModel> { viewModelFactory }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
    }

    companion object {
        fun newInstance() = UserFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userResult.observe(viewLifecycleOwner,
                Observer { userResult ->
                    userResult ?: return@Observer
                    userResult.error?.let { /* TODO Something */ }
                    userResult.success?.let { /* TODO Something */ }
                })

        viewModel.userFormState.observe(viewLifecycleOwner,
                Observer { userFormState ->
                    userFormState ?: return@Observer
                    userFormState.userFormStateError?.let { /* TODO Something */ }
                    userFormState.userFormStaterSuccess?.let { /* TODO Something */ }
                })
    }
}