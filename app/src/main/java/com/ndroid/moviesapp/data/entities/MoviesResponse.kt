package com.ndroid.moviesapp.data.entities

data class MoviesResponse(
    val page: Int,
    val results: MutableList<Movie>,
    val total_results: Int
)
