package com.fabianodev.voluntiers.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabianodev.voluntiers.domain.model.user.User
import com.fabianodev.voluntiers.domain.model.user.UserFormState
import com.fabianodev.voluntiers.domain.model.user.UserResult
import com.fabianodev.voluntiers.domain.usecase.UserUseCase
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    private val _userformState = MutableLiveData<UserFormState>()
    val userFormState: LiveData<UserFormState> = _userformState

    private val _userResult = MutableLiveData<UserResult>()
    val userResult: LiveData<UserResult> = _userResult


    suspend fun getUserById(userId: String): Result<User?> {
        return try {
            userUseCase.execute(userId)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}