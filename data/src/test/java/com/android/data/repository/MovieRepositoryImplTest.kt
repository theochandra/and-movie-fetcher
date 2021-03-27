package com.android.data.repository

import com.android.data.api.ServiceApi
import com.android.data.mapper.MovieMapper
import com.android.data.response.MovieListResponse
import com.android.domain.Result
import com.android.domain.model.MovieList
import com.android.domain.repository.MovieRepository
import com.nhaarman.mockito_kotlin.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response.success

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {

    @Mock
    lateinit var serviceApi: ServiceApi

    @Mock
    lateinit var mapper: MovieMapper

    private lateinit var sut: MovieRepository

    @Before
    fun setup() {
        sut = MovieRepositoryImpl(serviceApi, mapper)
    }

    @Test
    fun movieRepository_getMovieListResponse() {
        runBlocking {
            given(serviceApi.getMovieList(any(), any(), any()))
                .willReturn(mock())
            sut.getMovieList("testApiKey", "superman", 1)
            verify(serviceApi).getMovieList(any(), any(), any())
        }
    }

    @Test
    fun movieRepository_mapsMovieListResponse() {
        runBlocking {
            val movieListResponse = mock<MovieListResponse>()
            val response = success(movieListResponse)

            given(serviceApi.getMovieList(any(), any(), any()))
                .willReturn(response)
            given(mapper.map(any<MovieListResponse>()))
                .willReturn(mock())

            sut.getMovieList("testApiKey", "superman", 1)
            verify(mapper).map(eq(movieListResponse))
        }
    }

    @Test
    fun movieRepository_returnMovieList() {
        runBlocking {
            val movieListResponse = mock<MovieListResponse>()
            val response = success(movieListResponse)
            val movieList = mock<MovieList>()

            given(serviceApi.getMovieList(any(), any(), any()))
                .willReturn(response)
            given(mapper.map(any<MovieListResponse>()))
                .willReturn(movieList)

            val result = sut.getMovieList("testApiKey", "superman", 1)
            assertThat(result, equalTo(Result.Success(movieList)))
        }
    }

}