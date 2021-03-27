package com.android.data.repository

import com.android.data.api.ServiceApi
import com.android.data.mapper.MovieMapper
import com.android.data.safeApiCall
import com.android.domain.Result
import com.android.domain.model.MovieList
import com.android.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val serviceApi: ServiceApi,
    private val mapper: MovieMapper
) : MovieRepository {

    override suspend fun getMovieList(
        apiKey: String, query: String, pageNumber: Int)
    : Result<MovieList> {
        return safeApiCall(
            call = { getMovieListFromApi(apiKey, query, pageNumber) },
            errorMessage = "Exception occurred!"
        )
    }

    private suspend fun getMovieListFromApi(
        apiKey: String, query: String, pageNumber: Int
    ): Result<MovieList> {
        val result = serviceApi.getMovieList(apiKey, query, pageNumber)

        if (result.isSuccessful) {
            val body = result.body()
            body?.let {
                val movieList = mapper.map(it)
                return Result.Success(movieList)
            }
        }

        return Result.Error(result.code(), result.message())
    }

}