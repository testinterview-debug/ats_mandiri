package com.example.test_moviedb.presentation.genre

import com.example.test_moviedb.core.common.UiEffect
import com.example.test_moviedb.core.common.UiEvent
import com.example.test_moviedb.core.common.UiState
import com.example.test_moviedb.domain.model.Genre

data class GenreUiState(
    val isLoading: Boolean = false,
    val genres: List<Genre> = emptyList(),
    val error: String? = null
) : UiState

sealed interface GenreUiEvent : UiEvent {
    data object LoadGenres : GenreUiEvent
    data class OnGenreClick(val genre: Genre) : GenreUiEvent
}

sealed interface GenreUiEffect : UiEffect {
    data class NavigateToMovieList(val genreId: Int, val genreName: String) : GenreUiEffect
}
