package com.marcos012.movies.infra.projection

import com.marcos012.movies.model.MovieType
import java.util.Date

data class MovieListProjection(
    val title: String,
    val released: Date,
    val type: MovieType,
    val poster: String,
    val id: Long?
)