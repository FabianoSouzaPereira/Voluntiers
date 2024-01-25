package com.fabianodev.voluntiers.domain.repositories

import com.fabianodev.voluntiers.domain.model.home.Home

interface HomeRepository {
    suspend fun getHomeContent(id: String): Home
}