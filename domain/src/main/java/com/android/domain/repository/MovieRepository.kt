package com.android.domain.repository

import com.android.domain.Result
import com.android.domain.model.MovieList

interface MovieRepository {

    suspend fun getMovieList(): Result<MovieList>

}