package com.lightfeather.userswithfavorite.domain.model

sealed class DomainResponse<out T> {

    data class Success<out T>(val data: T) :
        DomainResponse<T>()

    data class Failure(val e: Exception) : DomainResponse<Nothing>()

}

fun <T> T.asSuccessDomainResponse() = DomainResponse.Success(this)

fun <T> T.asErrorDomainResponse(errorMessage: String) = Error(errorMessage)

inline fun <T> runDomainResponseRunnableCatchingHandled(block: () -> DomainResponse<T>) =
    runCatching(block).getOrElse { DomainResponse.Failure(Exception(it)) }
