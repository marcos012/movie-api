package com.marcos012.movies.service

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.infra.projection.MovieListProjection
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface IMovieQueryService {
    fun getAllMovies(page: PageRequest, title: String): Page<MovieListProjection>
    fun getMovie(id: Long): MovieDTO
    fun getMovieByTitle(title: String): MovieDTO
    fun searchMoviesByTitle(title: String): List<MovieListProjection>
}