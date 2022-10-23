package com.marcos012.movies.dto

import com.marcos012.movies.model.MovieType
import com.marcos012.movies.model.Rating
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal
import java.util.*


open class MovieDTO(
    var id: Long?,
    var title: String,
    var plot: String,
    var genre: String,
    var imdb: BigDecimal,
    var released: Date,
    var producer: String,
    var poster: String,
    var type: MovieType,
    var actors: String?,
    var director: String?,
    var runtime: String?,
    var ratings: List<MovieRatingDTO>,
    var totalSeasons: String?
) : RepresentationModel<MovieDTO>()
