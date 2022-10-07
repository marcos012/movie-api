package com.marcos012.movies.dto

import java.util.*

data class MovieDTO(
    val id: Long,
    val name: String,
    val description: String,
    val imdb: Double,
    val releaseDate: Date,
    val producer: String,
    val imageUrl: String
)
