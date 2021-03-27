package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.model.MovieList
import com.android.domain.repository.MovieRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMovieListUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var repository: MovieRepository

    private lateinit var sut: GetMovieListUseCase

    @Before
    fun setup() {
        sut = GetMovieListUseCase(repository)
    }

    @Test
    fun getMovieListUseCase_returnResultSuccess() {
        runBlocking {
            val movieList: MovieList = mock()
            given(repository.getMovieList(any(), any(), any()))
                .willReturn(Result.Success(movieList))
            sut.execute("testApiKey", "superman", 1)
            verify(repository).getMovieList("testApiKey", "superman", 1)
        }
    }

    @Test
    fun getMovieListUseCase_returnResultError() {
        runBlocking {
            given(repository.getMovieList(any(), any(), any()))
                .willReturn(Result.Error(500, "Internal server error"))
            sut.execute("testApiKey", "superman", 1)
            verify(repository).getMovieList("testApiKey", "superman", 1)
        }
    }

    @Test
    fun getMovieListUseCase_returnResultException() {
        runBlocking {
            val exception: Exception = mock()
            given(repository.getMovieList(any(), any(), any()))
                .willReturn(Result.Exception(exception))
            sut.execute("testApiKey", "superman", 1)
            verify(repository).getMovieList("testApiKey", "superman", 1)
        }
    }

}