package com.android.and_movie_fetcher.presentation.screen.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.and_movie_fetcher.presentation.mapper.MovieVmMapper
import com.android.and_movie_fetcher.presentation.observeOnce
import com.android.and_movie_fetcher.repository.FakeMovieRepositoryImpl
import com.android.domain.Result
import com.android.domain.model.Movie
import com.android.domain.usecase.GetMovieListUseCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MovieListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mapper: MovieVmMapper

    private lateinit var useCase: GetMovieListUseCase

    private lateinit var sut: MovieListViewModel

    private lateinit var closeable: AutoCloseable

    private val apiKey = "testApiKey"
    private val query = "superman"
    private val page = 1

    @Before
    fun setup() {
        closeable = MockitoAnnotations.openMocks(this)

        val fakeRepository = FakeMovieRepositoryImpl()
        useCase = GetMovieListUseCase(fakeRepository)
        sut = MovieListViewModel(useCase, mapper)
    }

    @After
    fun tearDown() {
        closeable.close()
    }

    @Test
    fun movieListViewModel_observeMovieVmList_returnResultSuccess() {
        var movieList = listOf<Movie>()
        runBlocking {
            when (val result = useCase.execute(apiKey, query, page)) {
                is Result.Success -> {
                    movieList = result.data.results
                }
            }
        }
        sut.movieVmList.observeOnce { movieVmList ->
            assertThat(movieVmList, equalTo(movieList.map { mapper.map(it) }))
        }
    }

}