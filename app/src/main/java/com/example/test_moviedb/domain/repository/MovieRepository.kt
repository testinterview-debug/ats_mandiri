package com.example.test_moviedb.domain.repository

import androidx.paging.PagingData
import com.example.test_moviedb.core.common.Resource
import com.example.test_moviedb.domain.model.Genre
import com.example.test_moviedb.domain.model.Movie
import com.example.test_moviedb.domain.model.MovieDetail
import com.example.test_moviedb.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getGenres(): Flow<Resource<List<Genre>>>
    
    fun getMoviesByGenre(genreId: Int): Flow<PagingData<Movie>>
    
    fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetail>>
    
    fun getMovieReviews(movieId: Int): Flow<PagingData<Review>>
    
    fun getMovieTrailers(movieId: Int): Flow<Resource<String?>> // Returns YouTube key
}