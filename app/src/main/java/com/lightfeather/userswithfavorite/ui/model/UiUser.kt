package com.lightfeather.userswithfavorite.ui.model

import com.lightfeather.userswithfavorite.domain.model.DomainUser
import kotlinx.serialization.Serializable


@Serializable
data class UiUser(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
    val isFavorite: Boolean,
)


fun DomainUser.toUiUser() = UiUser(
    id = id,
    name = name,
    username = username,
    email = email,
    phone = phone,
    website = website,
    isFavorite = isFavorite
)