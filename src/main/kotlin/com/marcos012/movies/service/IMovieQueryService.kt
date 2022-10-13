package com.marcos012.movies.service

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.infra.projection.OmdbMovieProjection
import com.marcos012.movies.infra.projection.OmdbMovieSearchProjection
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface IMovieQueryService {
    fun getAllMovies(page: PageRequest, title: String): Page<MovieDTO>
    fun getMovie(id: Long): MovieDTO
    fun getMovieByTitle(title: String): OmdbMovieProjection
    fun searchMoviesByTitle(title: String): OmdbMovieSearchProjection
}