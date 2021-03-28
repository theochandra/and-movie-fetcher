package com.android.domain.model

data class Movie(
    val id: Int,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String,
    val voteAverage: Double,
)
