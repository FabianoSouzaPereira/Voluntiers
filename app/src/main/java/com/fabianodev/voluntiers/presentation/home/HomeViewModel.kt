package com.fabianodev.voluntiers.presentation.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabianodev.voluntiers.R
import com.fabianodev.voluntiers.domain.model.home.Home
import com.fabianodev.voluntiers.domain.model.home.HomeFormState
import com.fabianodev.voluntiers.domain.model.home.HomeResult
import com.fabianodev.voluntiers.domain.model.home.HomeView
import com.fabianodev.voluntiers.domain.usecase.HomeUseCase
import com.fabianodev.voluntiers.utils.PreferenceManager
import javax.inject.Inject

class HomeViewModel @Inject constructor(context: Context, private val homeUseCase: HomeUseCase) : ViewModel() {
    private val preferenceManager: PreferenceManager = PreferenceManager(context)
    private val _homeFrom = MutableLiveData<HomeFormState>()
    val homeFormState: LiveData<HomeFormState> = _homeFrom
    private val _homeResult = MutableLiveData<HomeResult>()
    val homeResult: LiveData<HomeResult> = _homeResult

    suspend fun getHomeContent(id: String) {
        return try {
            val result: Home? = homeUseCase.execute(id = id)
            if (result != null) {
                _homeResult.value = HomeResult(success = HomeView(result.taskItem))
            } else {
                _homeResult.value = HomeResult(error = R.string.error_on_get_content)
            }
        } catch (e: IllegalArgumentException) {
            _homeResult.value = HomeResult(error = R.string.error_on_get_content)
        }
    }
}