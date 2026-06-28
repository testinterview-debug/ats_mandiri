package com.example.test_moviedb.data.remote

import com.example.test_moviedb.data.remote.dto.BaseResponse
import com.example.test_moviedb.data.remote.dto.GenreListResponse
import com.example.test_moviedb.data.remote.dto.MovieDto
import com.example.test_moviedb.data.remote.dto.MovieDetailDto
import com.example.test_moviedb.data.remote.dto.ReviewDto
import com.example.test_moviedb.data.remote.dto.VideoListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreListResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): BaseResponse<MovieDto>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailDto

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): BaseResponse<ReviewDto>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int
    ): VideoListResponse
}