package com.lightfeather.userswithfavorite.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DataBaseUser::class], version = 1)
abstract class FavoriteUsersDatabase : RoomDatabase() {
    abstract fun userDao(): FavoriteUsersDao
    companion object {
        @Volatile
        private var INSTANCE: FavoriteUsersDatabase? = null

        fun getDatabase(context: Context): FavoriteUsersDatabase {
            // Return existing instance or create a new one
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteUsersDatabase::class.java,
                    "app_database" // Name of your database
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}