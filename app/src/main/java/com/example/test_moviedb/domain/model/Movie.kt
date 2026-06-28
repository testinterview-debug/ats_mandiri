package com.example.test_moviedb.domain.model

import com.example.test_moviedb.BuildConfig

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double,
    val overview: String
) {
    val fullPosterPath: String
        get() = if (posterPath != null) "${BuildConfig.IMAGE_BASE_URL}w500$posterPath" else ""
}