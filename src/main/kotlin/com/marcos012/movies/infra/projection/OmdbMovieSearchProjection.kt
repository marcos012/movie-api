package com.marcos012.movies.infra.projection

import com.fasterxml.jackson.annotation.JsonProperty
import com.marcos012.movies.model.MovieType

data class SearchResult(val Title: String, val Year: String, val Type: MovieType, val Poster: String)

data class OmdbMovieSearchProjection(
    @JsonProperty("Search") val search: List<SearchResult>,
    val totalResults: String,
)