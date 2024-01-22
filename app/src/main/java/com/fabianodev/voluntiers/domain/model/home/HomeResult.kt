package com.fabianodev.voluntiers.domain.model.home

/**
 * Created by Fabiano Pereira on 20/01/2024.
 */
data class HomeResult(
        val success: HomeView? = null,
        val error: Int? = null
)