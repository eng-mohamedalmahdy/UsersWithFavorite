package com.lightfeather.userswithfavorite.ui.page.userlist

import com.lightfeather.userswithfavorite.ui.model.UiUser

sealed interface UsersPageIntent {
    data object Init : UsersPageIntent
    data object LoadUsers : UsersPageIntent
    data class ToggleFavorite(val user: UiUser) : UsersPageIntent
    data class NavigateToUser(val user: UiUser) : UsersPageIntent
}