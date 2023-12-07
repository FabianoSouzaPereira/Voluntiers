package com.fabianodev.voluntiers.data.home

import android.content.Context
import com.fabianodev.voluntiers.domain.repositories.HomeRepository
import javax.inject.Inject

class DefaultHomeRepositoryImpl @Inject constructor(val context: Context) : HomeRepository {
    override fun getUsers() {
        TODO("Not yet implemented")
    }
}