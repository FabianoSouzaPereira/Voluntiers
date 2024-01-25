package com.fabianodev.voluntiers.data.home

import com.fabianodev.voluntiers.dao.api.data.rest.IHomeApiService
import com.fabianodev.voluntiers.domain.model.home.Home
import com.fabianodev.voluntiers.domain.repositories.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteHomeDataSource @Inject constructor(private val apiService: IHomeApiService) : HomeRepository {
    override suspend fun getHomeContent(id: String): Home {
        return try {
            withContext(Dispatchers.IO) {
                val content: Home = apiService.getHomeContent(id = id)
                content
            }
        } catch (e: Throwable) {
            throw e
        }
    }
}