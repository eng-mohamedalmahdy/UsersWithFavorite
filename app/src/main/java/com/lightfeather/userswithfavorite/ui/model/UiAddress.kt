package com.lightfeather.userswithfavorite.ui.model

import com.lightfeather.userswithfavorite.domain.model.DomainAddress
import kotlinx.serialization.Serializable


@Serializable
data class UiAddress(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
)

fun DomainAddress.toUiAddress() = UiAddress(
    street = street, suite = suite, city = city, zipcode = zipcode
)