package com.lightfeather.userswithfavorite.domain.usecase

import com.lightfeather.userswithfavorite.domain.model.DomainResponse
import com.lightfeather.userswithfavorite.domain.model.DomainUser
import com.lightfeather.userswithfavorite.domain.repository.UsersRepository

data class GetFavoriteUsersUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(): DomainResponse<List<DomainUser>> {
        val favoriteUsersIds = (usersRepository.getFavoriteUsersIds() as? DomainResponse.Success)?.data
            ?: return DomainResponse.Failure(Exception("Failed to get favorite users ids"))

        val allUsers = (usersRepository.getAllUsers() as? DomainResponse.Success<List<DomainUser>>)?.data
            ?: return DomainResponse.Failure(Exception("Failed to get all users"))
        val favoriteUsers = allUsers.filter { it.id in favoriteUsersIds }

        return DomainResponse.Success(favoriteUsers)
    }
}
