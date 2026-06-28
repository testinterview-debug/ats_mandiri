package com.example.test_moviedb.presentation.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.test_moviedb.domain.model.Movie
import com.example.test_moviedb.presentation.common.components.ErrorScreen
import com.example.test_moviedb.presentation.common.components.ShimmerItem
import com.example.test_moviedb.presentation.movie.components.MovieItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    genreId: Int,
    genreName: String,
    onNavigateToDetail: (Int) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val movies: LazyPagingItems<Movie> = uiState.movies.collectAsLazyPagingItems()

    LaunchedEffect(genreId, genreName) {
        viewModel.onEvent(MovieUiEvent.LoadMovies(genreId, genreName))
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MovieUiEffect.NavigateToDetail -> onNavigateToDetail(effect.movieId)
                is MovieUiEffect.NavigateBack -> onNavigateBack()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = uiState.genreName,
                        fontWeight = FontWeight.Bold
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(MovieUiEvent.OnBackClick) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    count = movies.itemCount,
                    key = movies.itemKey { it.id }
                ) { index ->
                    movies[index]?.let { movie ->
                        MovieItem(
                            movie = movie,
                            onClick = { viewModel.onEvent(MovieUiEvent.OnMovieClick(movie.id)) }
                        )
                    }
                }

                when (val state = movies.loadState.append) {
                    is LoadState.Loading -> {
                        items(3) { ShimmerItem() }
                    }
                    is LoadState.Error -> {
                        item {
                            ErrorScreen(
                                message = state.error.message ?: "Failed to load more movies",
                                onRetry = { movies.retry() }
                            )
                        }
                    }
                    else -> {}
                }
            }

            // Initial load handling
            when (val state = movies.loadState.refresh) {
                is LoadState.Loading -> {
                    LazyColumn(contentPadding = PaddingValues(16.dp)) {
                        items(10) { ShimmerItem() }
                    }
                }
                is LoadState.Error -> {
                    ErrorScreen(
                        message = state.error.message ?: "Failed to load movies",
                        onRetry = { movies.retry() },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    if (movies.itemCount == 0 && state is LoadState.NotLoading) {
                        Text(
                            text = "No movies found",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}
