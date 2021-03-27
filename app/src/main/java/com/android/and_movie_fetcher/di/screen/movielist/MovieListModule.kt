package com.android.and_movie_fetcher.di.screen.movielist

import com.android.and_movie_fetcher.presentation.mapper.MovieVmMapper
import com.android.and_movie_fetcher.presentation.screen.movielist.MovieListViewModelFactory
import com.android.domain.usecase.GetMovieListUseCase
import dagger.Module
import dagger.Provides

@Module
class MovieListModule {

    @MovieListScope
    @Provides
    fun provideMovieListViewModelFactory(
        useCase: GetMovieListUseCase,
        mapper: MovieVmMapper
    ): MovieListViewModelFactory {
        return MovieListViewModelFactory(useCase, mapper)
    }

}