package com.marcos012.movies.infra.client

import com.marcos012.movies.infra.projection.OmdbMovieProjection
import com.marcos012.movies.infra.projection.OmdbMovieSearchProjection
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(url = "\${omdb.url}", name = "omdb")
interface IOmdbClient {
    @GetMapping
    fun getMovieByTitle(
        @RequestParam(value = "apiKey") apiKey: String,
        @RequestParam(value = "t") title: String?,
    ): OmdbMovieProjection

    @GetMapping
    fun searchMoviesByTitle(
        @RequestParam(value = "apiKey") apiKey: String,
        @RequestParam(value = "s") search: String?,
    ): OmdbMovieSearchProjection
}