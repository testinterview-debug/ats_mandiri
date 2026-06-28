package com.example.test_moviedb.presentation.movie

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.test_moviedb.core.common.BaseViewModel
import com.example.test_moviedb.domain.usecase.GetMoviesByGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
) : BaseViewModel<MovieUiState, MovieUiEvent, MovieUiEffect>(MovieUiState()) {

    override fun onEvent(event: MovieUiEvent) {
        when (event) {
            is MovieUiEvent.LoadMovies -> {
                updateState {
                    it.copy(
                        genreId = event.genreId,
                        genreName = event.genreName,
                        movies = getMoviesByGenreUseCase(event.genreId).cachedIn(viewModelScope)
                    )
                }
            }
            is MovieUiEvent.OnMovieClick -> {
                sendEffect(MovieUiEffect.NavigateToDetail(event.movieId))
            }
            is MovieUiEvent.OnBackClick -> {
                sendEffect(MovieUiEffect.NavigateBack)
            }
        }
    }
}
