package com.lightfeather.userswithfavorite.data.remote.model

import com.lightfeather.userswithfavorite.domain.model.DomainUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIUser(

    @SerialName("id") var id: Int? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("username") var username: String? = null,
    @SerialName("email") var email: String? = null,
    @SerialName("address") var address: APIAddress? = null,
    @SerialName("phone") var phone: String? = null,
    @SerialName("website") var website: String? = null,
    @SerialName("company") var company: APICompany? = null

)

fun APIUser.toDomainUser(): DomainUser? {
    return DomainUser(
        id = id.toString(),
        name = name.orEmpty(),
        username = username.orEmpty(),
        email = email.orEmpty(),
        phone = phone.orEmpty(),
        website = website.orEmpty(),
        address = address?.toDomainAddress() ?: return null,
        company = company?.toDomainCompany() ?: return null,
        isFavorite = false
    )
}