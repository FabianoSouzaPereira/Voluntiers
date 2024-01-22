package com.fabianodev.voluntiers.data.home

import android.content.Context
import com.fabianodev.voluntiers.domain.model.home.Home
import com.fabianodev.voluntiers.domain.repositories.HomeRepository
import javax.inject.Inject

class DefaultHomeRepositoryImpl @Inject constructor(val context: Context, private val dataSource: RemoteHomeDataSource) : HomeRepository {
    override suspend fun getHomeContent(): Home {
        try {
            return dataSource.getHomeContent()
        } catch (e: Throwable) {
            throw e
        }
    }
}