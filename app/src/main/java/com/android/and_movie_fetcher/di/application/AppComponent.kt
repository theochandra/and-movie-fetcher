package com.android.and_movie_fetcher.di.application

import com.android.and_movie_fetcher.di.screen.movielist.MovieListSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetModule::class,
    RepositoryModule::class
])
interface AppComponent {

    fun movieListSubComponent(): MovieListSubComponent.Factory

}