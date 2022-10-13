package com.marcos012.movies.infra.projection

import com.fasterxml.jackson.annotation.JsonProperty
import com.marcos012.movies.model.Rating
import java.util.Date

data class RatingProjection(
    val Source: String,
    val Value: String
)

data class OmdbMovieProjection(
    val Title: String,
    val Year: String,
    val Released: String,
    val Runtime: String,
    val Genre: String,
    val Director: String,
    val Actors: String,
    val Plot: String,
    val Poster: String,
    val Ratings: List<RatingProjection>,
    val imdbRating: String,
    val Type: String,
    val Production: String,
    val TotalSeasons: String?,
)