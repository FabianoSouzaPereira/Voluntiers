package com.fabianodev.voluntiers.domain.usecase

import com.fabianodev.voluntiers.domain.model.home.Home
import com.fabianodev.voluntiers.domain.repositories.HomeRepository
import javax.inject.Inject

/**
 * Created by Fabiano Pereira on 22/01/2024.
 */
class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun execute(): Home? {
        return try {
            homeRepository.getHomeContent()
        } catch (e: Throwable) {
            null
        }
    }
}