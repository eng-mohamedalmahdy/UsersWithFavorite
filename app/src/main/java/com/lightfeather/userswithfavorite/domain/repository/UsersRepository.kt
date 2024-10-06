package com.lightfeather.userswithfavorite.domain.repository

import com.lightfeather.userswithfavorite.domain.model.DomainResponse
import com.lightfeather.userswithfavorite.domain.model.DomainUser

interface UsersRepository {
    suspend fun getAllUsers(): DomainResponse<List<DomainUser>>
    suspend fun toggleFavorite(id: String): DomainResponse<Unit>
    suspend fun getFavoriteUsersIds(): DomainResponse<List<String>>
}