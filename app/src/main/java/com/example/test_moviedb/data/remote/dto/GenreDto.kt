package com.example.test_moviedb.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)

@Serializable
data class GenreListResponse(
    @SerialName("genres")
    val genres: List<GenreDto>
)