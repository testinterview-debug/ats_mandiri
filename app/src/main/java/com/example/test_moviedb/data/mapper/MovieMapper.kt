package com.example.test_moviedb.data.mapper

import com.example.test_moviedb.data.remote.dto.GenreDto
import com.example.test_moviedb.data.remote.dto.MovieDetailDto
import com.example.test_moviedb.data.remote.dto.MovieDto
import com.example.test_moviedb.data.remote.dto.ReviewDto
import com.example.test_moviedb.domain.model.Genre
import com.example.test_moviedb.domain.model.Movie
import com.example.test_moviedb.domain.model.MovieDetail
import com.example.test_moviedb.domain.model.Review

fun GenreDto.toDomain(): Genre {
    return Genre(id = id, name = name)
}

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage ?: 0.0,
        overview = overview ?: ""
    )
}

fun MovieDetailDto.toDomain(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        backdropPath = backdropPath,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage ?: 0.0,
        runtime = runtime ?: 0,
        genres = genres?.map { it.toDomain() } ?: emptyList(),
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        originalLanguage = originalLanguage ?: ""
    )
}

fun ReviewDto.toDomain(): Review {
    return Review(
        id = id,
        author = author,
        content = content,
        createdAt = createdAt,
        authorAvatarUrl = authorDetails?.avatarPath,
        rating = authorDetails?.rating
    )
}