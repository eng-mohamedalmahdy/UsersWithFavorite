package com.lightfeather.userswithfavorite.domain.usecase

import com.lightfeather.userswithfavorite.domain.repository.UsersRepository

data class ToggleUserFavoriteUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(userId: String) = usersRepository.toggleFavorite(userId)
}
