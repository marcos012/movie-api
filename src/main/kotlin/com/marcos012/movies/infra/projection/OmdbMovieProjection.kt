package com.marcos012.movies.infra.projection

import com.fasterxml.jackson.annotation.JsonProperty

data class OmdbMovieProjection(
    @JsonProperty("Title") val title: String,
    @JsonProperty("Year") val year: String,
//    val Rated: String,
//    val Released: Date,
//    val Runtime: String,
//    val Genre: String,
//    val Director: String,
//    val Writer: String,
//    val Actors: String,
//    val Plot: String,
//    val Language: String,
//    val Country: String,
//    val Awards: String,
//    val Poster: String,
//    val Ratings: List<String>,
//    val Metascore: String,
//    val imdbRating: String,
//    val imdbVotes: String,
//    val imdbID: String,
//    val Type: String,
//    val DVD: String,
//    val BoxOffice: String,
//    val Production: String,
//    val Website: String,
//    val Response: String
)