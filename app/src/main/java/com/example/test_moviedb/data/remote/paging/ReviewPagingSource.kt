package com.example.test_moviedb.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.test_moviedb.data.mapper.toDomain
import com.example.test_moviedb.data.remote.MovieApiService
import com.example.test_moviedb.domain.model.Review
import retrofit2.HttpException
import java.io.IOException

class ReviewPagingSource(
    private val remoteDataSource: MovieApiService,
    private val movieId: Int
) : PagingSource<Int, Review>() {

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val page = params.key ?: 1
        return try {
            val response = remoteDataSource.getMovieReviews(movieId, page)
            val reviews = response.results.map { it.toDomain() }
            
            LoadResult.Page(
                data = reviews,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (reviews.isEmpty() || page == response.totalPages) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}