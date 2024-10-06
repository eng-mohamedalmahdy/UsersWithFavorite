package com.lightfeather.userswithfavorite

import android.app.Application
import com.lightfeather.userswithfavorite.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class UsersWithFavoriteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Koin
        startKoin {
            androidLogger()
            androidContext(this@UsersWithFavoriteApplication)
            modules(appModule)
        }
    }
}