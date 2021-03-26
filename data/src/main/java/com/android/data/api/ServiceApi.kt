package com.android.data.api

import com.android.data.response.MovieListResponse
import retrofit2.Response
import javax.inject.Inject

class ServiceApi @Inject constructor(
    private val serviceEndPoint: ServiceEndPoint
) {

    suspend fun getMovieList(apiKey: String, pageNumber: Int): Response<MovieListResponse> {
        return serviceEndPoint.getMovieList(apiKey, pageNumber)
    }

}