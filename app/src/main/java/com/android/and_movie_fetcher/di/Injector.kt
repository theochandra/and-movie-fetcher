package com.android.and_movie_fetcher.di

import com.android.and_movie_fetcher.di.screen.movielist.MovieListSubComponent

interface Injector {

    fun createMovieListSubComponent(): MovieListSubComponent

}