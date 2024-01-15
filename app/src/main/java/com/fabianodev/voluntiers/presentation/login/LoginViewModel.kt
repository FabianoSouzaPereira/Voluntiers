package com.fabianodev.voluntiers.presentation.login


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabianodev.voluntiers.R
import com.fabianodev.voluntiers.domain.model.login.LoggedInUserView
import com.fabianodev.voluntiers.domain.model.login.LoginFormState
import com.fabianodev.voluntiers.domain.model.login.LoginResult
import com.fabianodev.voluntiers.domain.model.login.authenticationmodel.SignUpWithPassword
import com.fabianodev.voluntiers.domain.usecase.LoginUseCase
import com.fabianodev.voluntiers.utils.PreferenceManager
import javax.inject.Inject

class LoginViewModel @Inject constructor(context: Context, private val loginUseCase: LoginUseCase) : ViewModel() {
    private val preferenceManager: PreferenceManager = PreferenceManager(context)
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    suspend fun login(username: String, password: String, returnSecureToken: Boolean) {
        try {
            val result: SignUpWithPassword.SignUpResponse? = loginUseCase.execute(username, password, returnSecureToken)

            if (result != null) {
                _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.displayName))
                preferenceManager.savePreferenceString("token", result.idToken)
            } else {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        } catch (e: IllegalArgumentException) {
            _loginResult.value = LoginResult(error = R.string.validation_failed)
        }
    }


    fun loginDataChanged(username: String, password: String) {
        _loginForm.value = LoginFormState(
                usernameError = if (!isUserNameValid(username)) R.string.invalid_username else null,
                passwordError = if (!isPasswordValid(password)) R.string.invalid_password else null,
                isDataValid = isUserNameValid(username) && isPasswordValid(password)
        )
    }

    private fun isUserNameValid(username: String): Boolean {
        return loginUseCase.isUserNameValid(username)
    }

    private fun isPasswordValid(password: String): Boolean {
        return loginUseCase.isPasswordValid(password)
    }
}
