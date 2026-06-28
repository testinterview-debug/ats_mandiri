package com.example.test_moviedb.domain.model

import com.example.test_moviedb.BuildConfig

data class MovieDetail(
    val id: Int,
    val title: String,
    val backdropPath: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double,
    val runtime: Int,
    val genres: List<Genre>,
    val overview: String,
    val popularity: Double,
    val originalLanguage: String
) {
    val fullPosterPath: String
        get() = if (posterPath != null) "${BuildConfig.IMAGE_BASE_URL}w500$posterPath" else ""
    
    val fullBackdropPath: String
        get() = if (backdropPath != null) "${BuildConfig.IMAGE_BASE_URL}w780$backdropPath" else ""
}