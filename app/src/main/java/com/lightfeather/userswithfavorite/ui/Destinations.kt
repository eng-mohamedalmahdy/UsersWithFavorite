package com.lightfeather.userswithfavorite.ui

sealed class Destination {

    companion object {
        const val USERS_LIST_ROUTE = "users_list"
        const val USER_DETAIL_ROUTE = "user_detail"
        const val FAVORITE_USERS_ROUTE = "favorite_users"
    }

}