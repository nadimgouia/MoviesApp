package com.ndroid.moviesapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        val source: LiveData<Resource<T>> =
            databaseQuery.invoke().map { Resource.Success(it) }
        emitSource(source)
        val responseStatus = networkCall.invoke()
        if (responseStatus is Resource.Success) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus is Resource.Error) {
            emit(Resource.Error(responseStatus.exception))
            emitSource(source)
        }
    }