package com.fabianodev.voluntiers.presentation.login

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fabianodev.voluntiers.MainActivity
import com.fabianodev.voluntiers.R
import com.fabianodev.voluntiers.databinding.FragmentLoginBinding
import com.fabianodev.voluntiers.domain.model.login.LoggedInUserView
import com.fabianodev.voluntiers.utils.ButtonUtil
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var loginButton: Button
    lateinit var loadingProgressBar: ProgressBar

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        usernameEditText = binding.username
        passwordEditText = binding.password
        loginButton = binding.login
        loadingProgressBar = binding.loading

        setupButton(loginButton)

        viewModel.loginFormState.observe(
            viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })

        viewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
                    val navController = findNavController()
                    navController.navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                }
            })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                viewModel.loginDataChanged(
                    username = usernameEditText.text.toString(),
                    password = passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                lifecycleScope.launch {
                    viewModel.login(
                        username = usernameEditText.text.toString(),
                        password = passwordEditText.text.toString(),
                        returnSecureToken = true
                    )
                }
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            lifecycleScope.launch {
                viewModel.login(
                    username = usernameEditText.text.toString(),
                    password = passwordEditText.text.toString(),
                    returnSecureToken = true
                )
            }
        }
    }

    private fun setupButton(loginButton: Button) {
        ButtonUtil.setGradient(button = loginButton, textColor = Color.WHITE, buttonState = ButtonUtil.Companion.ButtonState.DEFAULT, radius = 20f)
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.loginFormState.removeObservers(viewLifecycleOwner)
        viewModel.loginResult.removeObservers(viewLifecycleOwner)
    }
}