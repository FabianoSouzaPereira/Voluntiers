package com.fabianodev.voluntiers.data.login

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class DataResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : DataResult<T>()
    data class Error(val exception: Exception) : DataResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "ErrorAuth[exception=$exception]"
        }
    }
}