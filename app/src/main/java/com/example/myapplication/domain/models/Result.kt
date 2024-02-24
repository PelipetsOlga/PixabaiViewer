package com.example.myapplication.domain.models

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed class Result<T> {

    data class OnSuccess<T>(val data: T) : Result<T>()
    data class OnError<T>(val error: Error) : Result<T>()

    companion object {

        fun <T> withValue(data: T): Result<T> = OnSuccess(data)

        fun <T> withError(error: Error): Result<T> = OnError(error)
    }
}

@OptIn(ExperimentalContracts::class)
fun <T> Result<T>.isSuccess(): Boolean {
    contract {
        returns(true) implies (this@isSuccess is Result.OnSuccess<T>)
        returns(false) implies (this@isSuccess is Result.OnError<T>)
    }
    return this is Result.OnSuccess<T>
}

@OptIn(ExperimentalContracts::class)
fun <T> Result<T>.isError(): Boolean {
    contract {
        returns(false) implies (this@isError is Result.OnSuccess<T>)
        returns(true) implies (this@isError is Result.OnError<T>)
    }
    return this is Result.OnError<T>
}

fun <T> Result<T>.getOrNull(): T? {
    return when (this) {
        is Result.OnError -> null
        is Result.OnSuccess -> data
    }
}

fun <T> Result<T>?.getOrNullForNullable(): T? {
    return when (this) {
        is Result.OnError -> null
        is Result.OnSuccess -> data
        else -> null
    }
}

inline fun <reified T : Error> Result<*>.errorOrNull(): T? {
    return if (this is Result.OnError<*> && error is T) {
        error
    } else {
        null
    }
}

inline fun <reified T> Result<*>.isErrorOfType(): Boolean {
    return this is Result.OnError<*> && this.error is T
}

@OptIn(ExperimentalContracts::class)
inline fun <R, T> Result<T>.fold(
    onSuccess: (data: T) -> R,
    onFailure: (error: Error) -> R
): R {
    contract {
        callsInPlace(onSuccess, InvocationKind.AT_MOST_ONCE)
        callsInPlace(onFailure, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is Result.OnSuccess -> onSuccess(data)
        is Result.OnError -> onFailure(error)
    }
}

fun <T> Error.asResult() = Result.withError<T>(this)

@OptIn(ExperimentalContracts::class)
inline fun <T, R> Result<T>.map(transform: (data: T) -> R): Result<R> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is Result.OnSuccess -> Result.withValue(transform(this.data))
        is Result.OnError -> Result.withError(this.error)
    }
}
