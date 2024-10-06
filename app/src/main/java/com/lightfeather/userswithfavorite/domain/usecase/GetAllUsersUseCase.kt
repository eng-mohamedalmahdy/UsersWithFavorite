package com.lightfeather.userswithfavorite.domain.usecase

import com.lightfeather.userswithfavorite.domain.model.DomainResponse
import com.lightfeather.userswithfavorite.domain.model.DomainUser
import com.lightfeather.userswithfavorite.domain.repository.UsersRepository

data class GetAllUsersUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(): DomainResponse<List<DomainUser>> {
        return usersRepository.getAllUsers()
    }
}
