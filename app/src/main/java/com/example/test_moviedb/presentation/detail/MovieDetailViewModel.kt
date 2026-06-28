package com.example.test_moviedb.presentation.detail

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.test_moviedb.core.common.BaseViewModel
import com.example.test_moviedb.core.common.Resource
import com.example.test_moviedb.domain.usecase.GetMovieDetailsUseCase
import com.example.test_moviedb.domain.usecase.GetMovieReviewsUseCase
import com.example.test_moviedb.domain.usecase.GetMovieTrailersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val getMovieTrailersUseCase: GetMovieTrailersUseCase
) : BaseViewModel<MovieDetailUiState, MovieDetailUiEvent, MovieDetailUiEffect>(MovieDetailUiState()) {

    override fun onEvent(event: MovieDetailUiEvent) {
        when (event) {
            is MovieDetailUiEvent.LoadMovieDetail -> {
                getMovieDetails(event.movieId)
                getMovieTrailer(event.movieId)
                loadReviews(event.movieId)
            }
            is MovieDetailUiEvent.OnBackClick -> sendEffect(MovieDetailUiEffect.NavigateBack)
            is MovieDetailUiEvent.OnWatchTrailer -> sendEffect(MovieDetailUiEffect.OpenYoutubeTrailer(event.trailerKey))
        }
    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> updateState { it.copy(isLoading = true, error = null) }
                    is Resource.Success -> updateState {
                        it.copy(isLoading = false, movieDetail = resource.data, error = null)
                    }
                    is Resource.Error -> updateState { it.copy(isLoading = false, error = resource.message) }
                }
            }
        }
    }

    private fun getMovieTrailer(movieId: Int) {
        viewModelScope.launch {
            getMovieTrailersUseCase(movieId).collect { resource ->
                if (resource is Resource.Success) {
                    updateState { it.copy(trailerKey = resource.data) }
                }
            }
        }
    }

    private fun loadReviews(movieId: Int) {
        updateState {
            it.copy(reviews = getMovieReviewsUseCase(movieId).cachedIn(viewModelScope))
        }
    }
}
