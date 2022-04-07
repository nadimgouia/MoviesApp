package com.ndroid.moviesapp.data.repositories

import com.ndroid.moviesapp.data.local.MovieDao
import com.ndroid.moviesapp.data.remote.MoviesRemoteDataSource
import com.ndroid.moviesapp.utils.performGetOperation
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val localDataSource: MovieDao
) {

    fun getAllPopularMovies() = performGetOperation(
        databaseQuery = { localDataSource.getAllMovies() },
        networkCall = { remoteDataSource.getAllPopularMovies() },
        saveCallResult = { localDataSource.insertAll(it.results)}
    )
}