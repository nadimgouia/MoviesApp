package com.ndroid.moviesapp.data.remote

import com.ndroid.moviesapp.data.entities.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val POSTER_PATH_SUFFIX = "https://image.tmdb.org/t/p/w342"
        const val API_KEY = "api key here"
    }

    @GET("popular?page=1")
    suspend fun getAllPopularMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MoviesResponse>

}