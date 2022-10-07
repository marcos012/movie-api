package com.marcos012.movies.infra.projection

import com.fasterxml.jackson.annotation.JsonProperty

data class OmdbMovieSearchProjection(
    @JsonProperty("Search") val search: List<OmdbMovieProjection>,
    val totalResults: String,
)