package com.example.test_moviedb.data.remote

import com.example.test_moviedb.data.remote.dto.BaseResponse
import com.example.test_moviedb.data.remote.dto.GenreListResponse
import com.example.test_moviedb.data.remote.dto.MovieDetailDto
import com.example.test_moviedb.data.remote.dto.MovieDto
import com.example.test_moviedb.data.remote.dto.ReviewDto
import com.example.test_moviedb.data.remote.dto.VideoListResponse
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val apiService: MovieApiService
) {
    suspend fun getGenres(): GenreListResponse = apiService.getGenres()

    suspend fun getMoviesByGenre(genreId: Int, page: Int): BaseResponse<MovieDto> =
        apiService.getMoviesByGenre(genreId, page)

    suspend fun getMovieDetails(movieId: Int): MovieDetailDto =
        apiService.getMovieDetails(movieId)

    suspend fun getMovieReviews(movieId: Int, page: Int): BaseResponse<ReviewDto> =
        apiService.getMovieReviews(movieId, page)

    suspend fun getMovieVideos(movieId: Int): VideoListResponse =
        apiService.getMovieVideos(movieId)
}
