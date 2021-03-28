package com.android.and_movie_fetcher.presentation.mapper

import com.android.and_movie_fetcher.presentation.model.MovieVM
import com.android.domain.model.Movie
import javax.inject.Inject

class MovieVmMapper @Inject constructor() {

    fun map(movie: Movie): MovieVM {
        return MovieVM(
            id = movie.id,
            overview = movie.overview,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            title = movie.title,
            voteAverage = movie.voteAverage
        )
    }

}