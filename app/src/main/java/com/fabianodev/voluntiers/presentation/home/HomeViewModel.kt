package com.fabianodev.voluntiers.presentation.home

import androidx.lifecycle.ViewModel
import com.fabianodev.voluntiers.domain.repositories.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val HomenRepository: HomeRepository) : ViewModel()