package com.intelehealth.ranjancodetest.data.remote

import retrofit2.Response

sealed class NetworkResult<out T> {
    data class Success<T>(val body: T) : NetworkResult<T>()
    data class Failure<T>(val errorResponse: Response<T>? = null) : NetworkResult<T>()
    data class Loading<T>(val loadings:Response<T>?=null):NetworkResult<T>()
}