package com.android.and_movie_fetcher.presentation.screen.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.and_movie_fetcher.presentation.mapper.MovieVmMapper
import com.android.domain.usecase.GetMovieListUseCase

class MovieListViewModelFactory(
    private val useCase: GetMovieListUseCase,
    private val mapper: MovieVmMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(useCase, mapper) as T
    }

}