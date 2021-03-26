package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.model.MovieList
import com.android.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend fun execute(): Result<MovieList> = repository.getMovieList()

}