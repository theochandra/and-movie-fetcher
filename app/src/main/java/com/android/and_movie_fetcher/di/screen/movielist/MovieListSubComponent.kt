package com.android.and_movie_fetcher.di.screen.movielist

import com.android.and_movie_fetcher.presentation.screen.movielist.MovieListActivity
import dagger.Subcomponent

@MovieListScope
@Subcomponent(
    modules = [
        MovieListModule::class
    ]
)
interface MovieListSubComponent {

    fun inject(movieListActivity: MovieListActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieListSubComponent
    }

}