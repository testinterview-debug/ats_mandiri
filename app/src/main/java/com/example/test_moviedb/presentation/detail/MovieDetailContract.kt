package com.example.test_moviedb.presentation.detail

import androidx.paging.PagingData
import com.example.test_moviedb.core.common.UiEffect
import com.example.test_moviedb.core.common.UiEvent
import com.example.test_moviedb.core.common.UiState
import com.example.test_moviedb.domain.model.MovieDetail
import com.example.test_moviedb.domain.model.Review
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    val trailerKey: String? = null,
    val reviews: Flow<PagingData<Review>> = emptyFlow(),
    val error: String? = null
) : UiState

sealed interface MovieDetailUiEvent : UiEvent {
    data class LoadMovieDetail(val movieId: Int) : MovieDetailUiEvent
    data object OnBackClick : MovieDetailUiEvent
    data class OnWatchTrailer(val trailerKey: String) : MovieDetailUiEvent
}

sealed interface MovieDetailUiEffect : UiEffect {
    data object NavigateBack : MovieDetailUiEffect
    data class OpenYoutubeTrailer(val trailerKey: String) : MovieDetailUiEffect
}
