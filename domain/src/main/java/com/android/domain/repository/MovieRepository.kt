package com.android.domain.repository

import com.android.domain.Result
import com.android.domain.model.MovieList

interface MovieRepository {

    suspend fun getMovieList(apiKey: String, query: String, pageNumber: Int): Result<MovieList>

}