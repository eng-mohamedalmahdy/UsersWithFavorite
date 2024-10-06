package com.lightfeather.userswithfavorite.ui.model

import com.lightfeather.userswithfavorite.domain.model.DomainCompany
import kotlinx.serialization.Serializable

@Serializable
data class UiCompany(
    val name: String,
    val catchPhrase: String,
    val bs: String,
)

fun DomainCompany.toUiCompany() = UiCompany(
    name = name,
    catchPhrase = catchPhrase,
    bs = bs,

)