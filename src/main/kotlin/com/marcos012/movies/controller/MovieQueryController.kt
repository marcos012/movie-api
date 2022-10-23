package com.marcos012.movies.controller

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.infra.projection.MovieListProjection
import com.marcos012.movies.infra.projection.OmdbMovieProjection
import com.marcos012.movies.infra.projection.OmdbMovieSearchProjection
import com.marcos012.movies.service.MovieQueryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MovieQueryController(val movieService: MovieQueryService) {

    @GetMapping("v1/movies")
    fun getAllMovies(
        @RequestParam(required = false, defaultValue = "") title: String,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "20") limit: Int,
        @RequestParam(required = false, defaultValue = "ASC") sort: Sort.Direction,
        @RequestParam(required = false, defaultValue = "title") orderBy: String,
    ): ResponseEntity<Page<MovieListProjection>> {
        val pageable = PageRequest.of(
            page,
            limit,
            Sort.by(sort, orderBy)
        )

        return ResponseEntity(movieService.getAllMovies(pageable, title), HttpStatus.OK)
    }

    @GetMapping("v1/movies/{id}")
    fun getMovie(@PathVariable id: Long): MovieDTO {
        return movieService.getMovie(id)
    }

    @GetMapping("v1/omdb/movies")
    fun searchMovieByTitle(@RequestParam(value = "title") title: String): List<MovieListProjection>  {
        return movieService.searchMoviesByTitle(title)
    }

    @GetMapping("v1/omdb/movie")
    fun getMovieByTitle(@RequestParam(value = "title") title: String): MovieDTO {
        return movieService.getMovieByTitle(title)
    }
}