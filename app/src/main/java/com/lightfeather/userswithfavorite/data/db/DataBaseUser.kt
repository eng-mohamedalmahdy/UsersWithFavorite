package com.lightfeather.userswithfavorite.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataBaseUser(
    @PrimaryKey val id: Int = 0,
    val isFavorite: Boolean
)
