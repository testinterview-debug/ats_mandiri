package com.example.test_moviedb.domain.usecase

import com.example.test_moviedb.core.common.Resource
import com.example.test_moviedb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieTrailersUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<String?>> = repository.getMovieTrailers(movieId)
}