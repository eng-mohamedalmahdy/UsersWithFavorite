package com.lightfeather.userswithfavorite.data.remote.model

import com.lightfeather.userswithfavorite.domain.model.DomainCompany
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APICompany(
    @SerialName("name") var name: String? = null,
    @SerialName("catchPhrase") var catchPhrase: String? = null,
    @SerialName("bs") var bs: String? = null

)

fun APICompany.toDomainCompany() = DomainCompany(
    name = name.orEmpty(),
    catchPhrase = catchPhrase.orEmpty(),
    bs = bs.orEmpty()
)