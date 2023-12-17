package com.fabianodev.voluntiers.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabianodev.voluntiers.domain.repositories.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val HomenRepository: HomeRepository) : ViewModel() {
    private val _fragmentText = MutableLiveData<String>()
    val fragmentTextState: LiveData<String> = _fragmentText.apply { "Home page" }

}