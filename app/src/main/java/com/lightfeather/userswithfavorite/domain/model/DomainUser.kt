package com.lightfeather.userswithfavorite.domain.model



data class DomainUser(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
    val address: DomainAddress,
    val company: DomainCompany,
    val isFavorite: Boolean,
)
