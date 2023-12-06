package com.fabianodev.voluntiers.data.home

import android.content.Context
import com.fabianodev.voluntiers.domain.repositories.IHomeRepository
import javax.inject.Inject

class IHomeRepositoryImpl @Inject constructor(val context: Context) : IHomeRepository {
    override fun getUsers() {
        TODO("Not yet implemented")
    }
}