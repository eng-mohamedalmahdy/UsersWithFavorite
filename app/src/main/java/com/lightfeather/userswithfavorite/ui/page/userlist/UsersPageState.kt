package com.lightfeather.userswithfavorite.ui.page.userlist

import com.lightfeather.userswithfavorite.ui.model.UiUser

data class UsersPageState(
    val isLoading: Boolean = false,
    val users: List<UiUser> = emptyList(),
    val error: String? = null,
)
