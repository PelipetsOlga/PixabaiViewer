package com.example.domain.models

sealed class Error(
    val message: String,
    val throwable: Throwable? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Error

        if (message != other.message) return false
        return throwable == other.throwable
    }

    override fun hashCode(): Int {
        var result = 23
        result = 31 * result + message.hashCode()
        result = 31 * result + (throwable?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String = "Error(message='$message', throwable=$throwable)"
}

open class UnknownError(
    message: String = "Unknown Error",
    throwable: Throwable? = null
) : Error(message, throwable)

open class NetworkError(
    message: String = "Network Error",
    throwable: Throwable? = null
) : Error(message, throwable)

class NoNetworkError(
    message: String = "Network Error",
    throwable: Throwable? = null
) : Error(message, throwable)

abstract class FeatureError(
    message: String,
    throwable: Throwable? = null
) : Error(message, throwable)
