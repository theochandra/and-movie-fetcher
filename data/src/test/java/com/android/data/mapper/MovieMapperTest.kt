package com.android.data.mapper

import com.android.data.response.MovieListResponse
import com.android.data.response.MovieResponse
import com.android.domain.model.Movie
import com.android.domain.model.MovieList
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class MovieMapperTest {

    private lateinit var sut: MovieMapper

    private lateinit var movieListResponse: MovieListResponse
    private lateinit var movieResponse: MovieResponse

    private lateinit var movieList: MovieList
    private lateinit var movie: Movie

    @Before
    fun setup() {
        sut = MovieMapper()

        movieResponse = MovieResponse(
            id = 13640,
            overview = "When LexCorp accidentally unleashes a murderous creature, Superman meets " +
                    "his greatest challenge as a champion. Based on the \"The Death of Superman\" " +
                    "storyline that appeared in DC Comics' publications in the 1990s.",
            posterPath = "/itvuWm7DFWWzWgW0xgiaKzzWszP.jpg",
            releaseDate = "2007-09-18",
            title = "Superman: Doomsday"
        )

        movieListResponse = MovieListResponse(
            page = 1,
            results = listOf(movieResponse),
            totalPages = 8,
            totalResults = 156
        )

        movie = Movie(
            id = 13640,
            overview = "When LexCorp accidentally unleashes a murderous creature, Superman meets " +
                    "his greatest challenge as a champion. Based on the \"The Death of Superman\" " +
                    "storyline that appeared in DC Comics' publications in the 1990s.",
            posterPath = "/itvuWm7DFWWzWgW0xgiaKzzWszP.jpg",
            releaseDate = "2007-09-18",
            title = "Superman: Doomsday"
        )

        movieList = MovieList(
            page = 1,
            results = listOf(movie),
            totalPages = 8,
            totalResults = 156
        )
    }

    @Test
    fun movieMapper_mapsMovieResponse_returnMovie() {
        val result = sut.map(movieResponse)
        assertThat(result, equalTo(movie))
    }

    @Test
    fun movieMapper_mapsMovieResponseId_returnMovieId() {
        val result = sut.map(movieResponse)
        assertThat(result.id, equalTo(movie.id))
    }

    @Test
    fun movieMapper_mapsMovieResponseOverview_returnMovieOverview() {
        val result = sut.map(movieResponse)
        assertThat(result.overview, equalTo(movie.overview))
    }

    @Test
    fun movieMapper_mapsMovieResponsePosterPath_returnMoviePosterPath() {
        val result = sut.map(movieResponse)
        assertThat(result.posterPath, equalTo(movie.posterPath))
    }

    @Test
    fun movieMapper_mapsMovieResponseReleaseDate_returnMovieReleaseDate() {
        val result = sut.map(movieResponse)
        assertThat(result.releaseDate, equalTo(movie.releaseDate))
    }

    @Test
    fun movieMapper_mapsMovieResponseTitle_returnMovieTitle() {
        val result = sut.map(movieResponse)
        assertThat(result.title, equalTo(movie.title))
    }

    @Test
    fun movieMapper_mapsMovieListResponse_returnMovieList() {
        val result = sut.map(movieListResponse)
        assertThat(result, equalTo(movieList))
    }

    @Test
    fun movieMapper_mapsMovieListResponsePage_returnMovieListPage() {
        val result = sut.map(movieListResponse)
        assertThat(result.page, equalTo(movieList.page))
    }

    @Test
    fun movieMapper_mapsMovieListResponseResults_returnMovieListResults() {
        val result = sut.map(movieListResponse)
        assertThat(result.results, equalTo(movieList.results))
    }

    @Test
    fun movieMapper_mapsMovieListResponseTotalPages_returnMovieListTotalPages() {
        val result = sut.map(movieListResponse)
        assertThat(result.totalPages, equalTo(movieList.totalPages))
    }

    @Test
    fun movieMapper_mapsMovieListResponseTotalResults_returnMovieListTotalResults() {
        val result = sut.map(movieListResponse)
        assertThat(result.totalResults, equalTo(movieList.totalResults))
    }

}