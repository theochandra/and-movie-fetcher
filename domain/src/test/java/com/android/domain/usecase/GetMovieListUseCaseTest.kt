package com.android.domain.usecase

import com.android.domain.Result
import com.android.domain.coroutines.CoroutineTestRule
import com.android.domain.model.MovieList
import com.android.domain.repository.MovieRepository
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
            given(repository.getMovieList())
                .willReturn(Result.Success(movieList))
            sut.execute()
            verify(repository).getMovieList()
        }
    }

    @Test
    fun getMovieListUseCase_returnResultError() {
        runBlocking {
            given(repository.getMovieList())
                .willReturn(Result.Error(500, "Internal server error"))
            sut.execute()
            verify(repository).getMovieList()
        }
    }

    @Test
    fun getMovieListUseCase_returnResultException() {
        runBlocking {
            val exception: Exception = mock()
            given(repository.getMovieList())
                .willReturn(Result.Exception(exception))
            sut.execute()
            verify(repository).getMovieList()
        }
    }

}