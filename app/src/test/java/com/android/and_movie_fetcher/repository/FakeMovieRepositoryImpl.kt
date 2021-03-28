package com.android.and_movie_fetcher.repository

import com.android.domain.Result
import com.android.domain.model.MovieList
import com.android.domain.repository.MovieRepository
import com.nhaarman.mockito_kotlin.mock

class FakeMovieRepositoryImpl : MovieRepository {

    override suspend fun getMovieList(
        apiKey: String,
        query: String,
        pageNumber: Int
    ): Result<MovieList> {
        val movieList: MovieList = mock()
        return Result.Success(movieList)
    }

}