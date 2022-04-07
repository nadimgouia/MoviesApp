package com.ndroid.moviesapp.data.remote

import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val moviesApi: MoviesApi
): BaseDataSource() {
    suspend fun getAllPopularMovies() = getResult { moviesApi.getAllPopularMovies() }
}
