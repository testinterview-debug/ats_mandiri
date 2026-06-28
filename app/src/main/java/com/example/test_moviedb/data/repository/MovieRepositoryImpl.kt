package com.example.test_moviedb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.test_moviedb.core.common.Resource
import com.example.test_moviedb.core.network.safeApiCall
import com.example.test_moviedb.data.mapper.toDomain
import com.example.test_moviedb.data.remote.MovieApiService
import com.example.test_moviedb.data.remote.paging.MoviePagingSource
import com.example.test_moviedb.data.remote.paging.ReviewPagingSource
import com.example.test_moviedb.domain.model.Genre
import com.example.test_moviedb.domain.model.Movie
import com.example.test_moviedb.domain.model.MovieDetail
import com.example.test_moviedb.domain.model.Review
import com.example.test_moviedb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService
) : MovieRepository {

    override fun getGenres(): Flow<Resource<List<Genre>>> = safeApiCall {
        apiService.getGenres().genres.map { it.toDomain() }
    }

    override fun getMoviesByGenre(genreId: Int): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { MoviePagingSource(apiService, genreId) }
        ).flow
    }

    override fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetail>> = safeApiCall {
        apiService.getMovieDetails(movieId).toDomain()
    }

    override fun getMovieReviews(movieId: Int): Flow<PagingData<Review>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { ReviewPagingSource(apiService, movieId) }
        ).flow
    }

    override fun getMovieTrailers(movieId: Int): Flow<Resource<String?>> = safeApiCall {
        val response = apiService.getMovieVideos(movieId)
        response.results.firstOrNull {
            it.site == "YouTube" && it.type == "Trailer" && it.official
        }?.key
    }
}
