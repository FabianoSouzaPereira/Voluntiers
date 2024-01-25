package com.fabianodev.voluntiers.dao.api.data.rest

import com.fabianodev.voluntiers.domain.model.home.Home
import com.fabianodev.voluntiers.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface IHomeApiService {

    @GET(Constants.Endpoint.GET_TASKS)
    suspend fun getHomeContent(@Path("id") id: String): Home
}