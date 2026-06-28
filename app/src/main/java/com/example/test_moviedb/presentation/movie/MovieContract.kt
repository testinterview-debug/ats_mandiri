package com.example.test_moviedb.presentation.movie

import androidx.paging.PagingData
import com.example.test_moviedb.core.common.UiEffect
import com.example.test_moviedb.core.common.UiEvent
import com.example.test_moviedb.core.common.UiState
import com.example.test_moviedb.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieUiState(
    val genreId: Int = -1,
    val genreName: String = "",
    val movies: Flow<PagingData<Movie>> = emptyFlow()
) : UiState

sealed interface MovieUiEvent : UiEvent {
    data class LoadMovies(val genreId: Int, val genreName: String) : MovieUiEvent
    data class OnMovieClick(val movieId: Int) : MovieUiEvent
    data object OnBackClick : MovieUiEvent
}

sealed interface MovieUiEffect : UiEffect {
    data class NavigateToDetail(val movieId: Int) : MovieUiEffect
    data object NavigateBack : MovieUiEffect
}
