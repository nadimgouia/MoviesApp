package com.ndroid.moviesapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ndroid.moviesapp.data.remote.MoviesApi

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val id: Long,
    val title: String,
    val poster_path: String
) {
    val completePosterPath: String
        get() = "${MoviesApi.POSTER_PATH_SUFFIX}$poster_path"
}