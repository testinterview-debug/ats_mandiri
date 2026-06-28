package com.example.test_moviedb.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoute {
    @Serializable
    data object GenreList : NavRoute

    @Serializable
    data class MovieList(val genreId: Int, val genreName: String) : NavRoute

    @Serializable
    data class MovieDetail(val movieId: Int) : NavRoute
}