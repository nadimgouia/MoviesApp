package com.ndroid.moviesapp.data.remote

import com.ndroid.moviesapp.utils.Resource
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.Success(body)
            }
            return error(Exception("${response.code()} ${response.message()}"))
        } catch (e: Exception) {
            return error(e)
        }
    }

    private fun <T> error(e: Exception): Resource<T> {
        Timber.d(e.message ?: "")
        return Resource.Error(e)
    }

}