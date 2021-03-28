package com.android.and_movie_fetcher.presentation.mapper

import com.android.and_movie_fetcher.presentation.model.MovieVM
import com.android.domain.model.Movie
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class MovieVmMapperTest {

    private lateinit var sut: MovieVmMapper

    private lateinit var movie: Movie

    private lateinit var movieVM: MovieVM

    @Before
    fun setup() {
        sut = MovieVmMapper()

        movie = Movie(
            id = 13640,
            overview = "When LexCorp accidentally unleashes a murderous creature, Superman meets " +
                    "his greatest challenge as a champion. Based on the \"The Death of Superman\" " +
                    "storyline that appeared in DC Comics' publications in the 1990s.",
            posterPath = "/itvuWm7DFWWzWgW0xgiaKzzWszP.jpg",
            releaseDate = "2007-09-18",
            title = "Superman: Doomsday",
            voteAverage = 7.8
        )

        movieVM = MovieVM(
            id = 13640,
            overview = "When LexCorp accidentally unleashes a murderous creature, Superman meets " +
                    "his greatest challenge as a champion. Based on the \"The Death of Superman\" " +
                    "storyline that appeared in DC Comics' publications in the 1990s.",
            posterPath = "/itvuWm7DFWWzWgW0xgiaKzzWszP.jpg",
            releaseDate = "2007-09-18",
            title = "Superman: Doomsday",
            voteAverage = 7.8
        )
    }

    @Test
    fun movieVmMapper_mapsMovie_returnMovieVm() {
        val result = sut.map(movie)
        MatcherAssert.assertThat(result, CoreMatchers.equalTo(movieVM))
    }

    @Test
    fun movieVmMapper_mapsMovieId_returnMovieVmId() {
        val result = sut.map(movie)
        MatcherAssert.assertThat(result.id, CoreMatchers.equalTo(movieVM.id))
    }

    @Test
    fun movieVmMapper_mapsMovieOverview_returnMovieVmOverview() {
        val result = sut.map(movie)
        MatcherAssert.assertThat(result.overview, CoreMatchers.equalTo(movieVM.overview))
    }

    @Test
    fun movieVmMapper_mapsMoviePosterPath_returnMovieVmPosterPath() {
        val result = sut.map(movie)
        MatcherAssert.assertThat(result.posterPath, CoreMatchers.equalTo(movieVM.posterPath))
    }

    @Test
    fun movieVmMapper_mapsMovieReleaseDate_returnMovieVmReleaseDate() {
        val result = sut.map(movie)
        MatcherAssert.assertThat(result.releaseDate, CoreMatchers.equalTo(movieVM.releaseDate))
    }

    @Test
    fun movieVmMapper_mapsMovieTitle_returnMovieVmTitle() {
        val result = sut.map(movie)
        MatcherAssert.assertThat(result.title, CoreMatchers.equalTo(movieVM.title))
    }

    @Test
    fun movieVmMapper_mapsMovieVoteAverage_returnMovieVmVoteAverage() {
        val result = sut.map(movie)
        MatcherAssert.assertThat(result.voteAverage, CoreMatchers.equalTo(movieVM.voteAverage))
    }

}