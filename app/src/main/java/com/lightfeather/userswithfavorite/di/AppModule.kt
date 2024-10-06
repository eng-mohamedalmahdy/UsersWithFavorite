package com.lightfeather.userswithfavorite.di

import com.lightfeather.userswithfavorite.data.UsersRepositoryProductionImp
import com.lightfeather.userswithfavorite.data.db.FavoriteUsersDatabase
import com.lightfeather.userswithfavorite.domain.repository.UsersRepository
import com.lightfeather.userswithfavorite.domain.usecase.GetAllUsersUseCase
import com.lightfeather.userswithfavorite.domain.usecase.GetFavoriteUsersUseCase
import com.lightfeather.userswithfavorite.domain.usecase.ToggleUserFavoriteUseCase
import com.lightfeather.userswithfavorite.ui.page.userlist.UsersPageViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<UsersRepository> { UsersRepositoryProductionImp(get()) }
    factory { ToggleUserFavoriteUseCase(get()) }
    factory { GetAllUsersUseCase(get()) }
    factory { GetFavoriteUsersUseCase(get()) }

    single { FavoriteUsersDatabase.getDatabase(androidContext().applicationContext) }
    factory { get<FavoriteUsersDatabase>().userDao() }

    viewModel {
        UsersPageViewModel(
            source = it.get(),
            toggleUserFavoriteUseCase = get(),
            getAllUsersUseCase = get(),
            getFavoriteUsersUseCase = get()
        )
    }

}