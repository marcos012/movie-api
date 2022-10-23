package com.marcos012.movies.infra.projection

import com.fasterxml.jackson.annotation.JsonProperty

data class SearchResult(val Title: String, val Year: String, val Type: String, val Poster: String)

data class OmdbMovieSearchProjection(
    @JsonProperty("Search") val search: List<SearchResult>,
    val totalResults: String,
)