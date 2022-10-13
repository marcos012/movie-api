package com.marcos012.movies.infra.projection

import com.marcos012.movies.model.MovieType

data class MovieListProjection(
    val title: String,
    val released: String,
    val type: MovieType,
    val poster: String,
    val id: Long?
)