package com.fabianodev.voluntiers.dao.api.data.rest

import com.fabianodev.voluntiers.domain.model.home.Home
import retrofit2.http.GET

interface IHomeApiService {

    @GET("")
    suspend fun getHomeContent(): Home
}