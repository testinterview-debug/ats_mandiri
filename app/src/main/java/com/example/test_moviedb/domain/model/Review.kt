package com.example.test_moviedb.domain.model

data class Review(
    val id: String,
    val author: String,
    val content: String,
    val createdAt: String,
    val authorAvatarUrl: String?,
    val rating: Double?
)