package com.lightfeather.userswithfavorite.data

import com.lightfeather.userswithfavorite.data.db.DataBaseUser
import com.lightfeather.userswithfavorite.data.db.FavoriteUsersDao
import com.lightfeather.userswithfavorite.data.remote.HttpClient
import com.lightfeather.userswithfavorite.data.remote.model.toDomainUser
import com.lightfeather.userswithfavorite.domain.model.DomainResponse
import com.lightfeather.userswithfavorite.domain.model.DomainUser
import com.lightfeather.userswithfavorite.domain.model.asSuccessDomainResponse
import com.lightfeather.userswithfavorite.domain.model.runDomainResponseRunnableCatchingHandled
import com.lightfeather.userswithfavorite.domain.repository.UsersRepository

class UsersRepositoryProductionImp(
    private val favoriteUsersDao: FavoriteUsersDao
) : UsersRepository {
    override suspend fun getAllUsers(): DomainResponse<List<DomainUser>> = runDomainResponseRunnableCatchingHandled {
        val apiUsers = HttpClient.loadUsers()
        val favoriteUsersIds = favoriteUsersDao.getAllFavoriteUsers().filter { it.isFavorite }.map { it.id }
        apiUsers.mapNotNull { it.toDomainUser()?.copy(isFavorite = it.id in favoriteUsersIds) }.asSuccessDomainResponse()
    }

    override suspend fun toggleFavorite(id: String): DomainResponse<Unit> {
        val isFavorite = favoriteUsersDao.getFavoriteUserById(id.toInt()) != null
        favoriteUsersDao.updateFavoriteStatus(DataBaseUser(id.toInt(), !isFavorite))
        return Unit.asSuccessDomainResponse()
    }

    override suspend fun getFavoriteUsersIds(): DomainResponse<List<String>> {
        return favoriteUsersDao.getAllFavoriteUsers().map { it.id.toString() }.asSuccessDomainResponse()
    }
}