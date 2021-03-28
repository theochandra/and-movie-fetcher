package com.android.data.mapper

import androidx.annotation.VisibleForTesting
import com.android.data.response.MovieListResponse
import com.android.data.response.MovieResponse
import com.android.domain.model.Movie
import com.android.domain.model.MovieList
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun map(response: MovieListResponse): MovieList {
        return MovieList(
            page = response.page,
            results = response.results.map { map(it) },
            totalPages = response.totalPages,
            totalResults = response.totalResults
        )
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun map(movieResponse: MovieResponse): Movie {
        return Movie(
            id = movieResponse.id,
            overview = movieResponse.overview,
            posterPath = movieResponse.posterPath,
            releaseDate = movieResponse.releaseDate,
            title = movieResponse.title,
            voteAverage = movieResponse.voteAverage
        )
    }

}