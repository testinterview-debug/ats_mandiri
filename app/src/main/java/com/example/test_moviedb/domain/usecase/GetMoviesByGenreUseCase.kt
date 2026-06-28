package com.example.test_moviedb.domain.usecase

import androidx.paging.PagingData
import com.example.test_moviedb.domain.model.Movie
import com.example.test_moviedb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesByGenreUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(genreId: Int): Flow<PagingData<Movie>> = repository.getMoviesByGenre(genreId)
}