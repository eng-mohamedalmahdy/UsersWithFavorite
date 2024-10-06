package com.lightfeather.userswithfavorite.data.remote.model

import com.lightfeather.userswithfavorite.domain.model.DomainAddress
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIAddress(

    @SerialName("street") var street: String? = null,
    @SerialName("suite") var suite: String? = null,
    @SerialName("city") var city: String? = null,
    @SerialName("zipcode") var zipcode: String? = null,
    @SerialName("geo") var geo: Geo? = Geo()

) {
    @Serializable
    data class Geo(

        @SerialName("lat") var lat: String? = null,
        @SerialName("lng") var lng: String? = null

    )
}

fun APIAddress.toDomainAddress() = DomainAddress(
    street = street.orEmpty(),
    suite = suite.orEmpty(),
    city = city.orEmpty(),
    zipcode = zipcode.orEmpty(),
    geo = (geo?.lat?.toDoubleOrNull() ?: 0.0) to (geo?.lng?.toDoubleOrNull() ?: 0.0)

)