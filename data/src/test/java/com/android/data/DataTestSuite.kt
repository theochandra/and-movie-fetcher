package com.android.data

import com.android.data.mapper.MovieMapperTest
import com.android.data.repository.MovieRepositoryImplTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MovieMapperTest::class,
    MovieRepositoryImplTest::class
)
class DataTestSuite