package com.example.test_moviedb.domain.usecase

import com.example.test_moviedb.core.common.Resource
import com.example.test_moviedb.domain.model.MovieDetail
import com.example.test_moviedb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieDetail>> = repository.getMovieDetails(movieId)
}