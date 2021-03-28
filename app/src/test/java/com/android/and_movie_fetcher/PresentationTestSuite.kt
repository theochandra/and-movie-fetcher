package com.android.and_movie_fetcher

import com.android.and_movie_fetcher.presentation.mapper.MovieVmMapperTest
import com.android.and_movie_fetcher.presentation.screen.movielist.MovieListViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MovieVmMapperTest::class,
    MovieListViewModelTest::class
)
class PresentationTestSuite