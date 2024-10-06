package com.lightfeather.userswithfavorite.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FavoriteUsersDao {

    @Query("SELECT * FROM DataBaseUser WHERE isFavorite = 1")
    suspend fun getAllFavoriteUsers(): List<DataBaseUser>

    @Query("SELECT * FROM DataBaseUser WHERE id = :userId")
    suspend fun getFavoriteUserById(userId: Int): DataBaseUser?

    @Upsert
    suspend fun updateFavoriteStatus(user: DataBaseUser)

}