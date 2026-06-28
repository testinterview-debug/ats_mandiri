package com.example.test_moviedb.presentation.genre

import androidx.lifecycle.viewModelScope
import com.example.test_moviedb.core.common.BaseViewModel
import com.example.test_moviedb.core.common.Resource
import com.example.test_moviedb.domain.usecase.GetGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase
) : BaseViewModel<GenreUiState, GenreUiEvent, GenreUiEffect>(GenreUiState()) {

    init {
        onEvent(GenreUiEvent.LoadGenres)
    }

    override fun onEvent(event: GenreUiEvent) {
        when (event) {
            is GenreUiEvent.LoadGenres -> getGenres()
            is GenreUiEvent.OnGenreClick -> {
                sendEffect(GenreUiEffect.NavigateToMovieList(event.genre.id, event.genre.name))
            }
        }
    }

    private fun getGenres() {
        viewModelScope.launch {
            getGenresUseCase().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        updateState { it.copy(isLoading = true, error = null) }
                    }
                    is Resource.Success -> {
                        updateState {
                            it.copy(isLoading = false, genres = resource.data, error = null)
                        }
                    }
                    is Resource.Error -> {
                        updateState { it.copy(isLoading = false, error = resource.message) }
                    }
                }
            }
        }
    }
}
