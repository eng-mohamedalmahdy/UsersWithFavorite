package com.lightfeather.userswithfavorite.domain.model

data class DomainAddress(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Pair<Double, Double>,
)
