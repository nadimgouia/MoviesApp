package com.ndroid.moviesapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndroid.moviesapp.data.entities.Movie
import com.ndroid.moviesapp.data.entities.MoviesResponse
import com.ndroid.moviesapp.data.repositories.MoviesRepository
import com.ndroid.moviesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private var _movies = MutableLiveData<Resource<List<Movie>>>()
    val movies: LiveData<Resource<List<Movie>>>
        get() = _movies

    init {
        loadMovies()
    }

    fun loadMovies() {
        _movies = repository.getAllPopularMovies() as MutableLiveData<Resource<List<Movie>>>
    }


}