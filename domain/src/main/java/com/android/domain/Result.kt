package com.android.domain

sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val errorCode: Int, val errorMessage: String) : Result<Nothing>()
    data class Exception(val exception: kotlin.Exception) : Result<Nothing>()
}