package com.android.data.api

import com.android.data.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceEndPoint {

    @GET
    suspend fun getMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ): Response<MovieListResponse>

}