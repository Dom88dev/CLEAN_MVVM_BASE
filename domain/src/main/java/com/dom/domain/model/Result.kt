package com.dom.domain.model

sealed class Result<out T> {
    object NotInit: Result<Nothing>()
    object Loading: Result<Nothing>()
    data class Success<T>(val data: T): Result<T>()
    sealed class Failure : Result<Nothing>() {
        data class Error(val code: Int, val message: String?) : Failure()
        data class Exception(val e: Throwable) : Failure()
    }
}
