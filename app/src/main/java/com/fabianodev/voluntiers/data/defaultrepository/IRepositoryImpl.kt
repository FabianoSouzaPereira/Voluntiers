package com.fabianodev.voluntiers.data.defaultrepository

import android.content.Context
import android.util.Log
import com.fabianodev.voluntiers.domain.repositories.IRepository
import javax.inject.Inject

class IRepositoryImpl @Inject constructor(val context: Context) : IRepository {
    override fun login() {
        Log.d("IRepositoryImpl", "Login Done")
    }
}