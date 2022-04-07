package com.ndroid.moviesapp.di

import android.content.Context
import com.ndroid.moviesapp.data.local.AppDatabase
import com.ndroid.moviesapp.data.remote.MoviesApi
import com.ndroid.moviesapp.data.remote.MoviesRemoteDataSource
import com.ndroid.moviesapp.ui.home.MoviesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMoviesAdapter(): MoviesAdapter = MoviesAdapter()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    @Provides
    @Singleton
    fun provideMoviesRemoteDataSource(moviesApi: MoviesApi) = MoviesRemoteDataSource(moviesApi)


}