package com.marcos012.movies.service

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.infra.client.IOmdbClient
import com.marcos012.movies.infra.projection.OmdbMovieProjection
import com.marcos012.movies.infra.projection.OmdbMovieSearchProjection
import com.marcos012.movies.mappers.MovieMapper
import com.marcos012.movies.infra.repository.MovieRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class MovieQueryService(
    private val movieRepository: MovieRepository,
    private val omdbClient: IOmdbClient
) {

    @Value("\${omdb.apikey}")
    val apiKey: String = ""

    fun getAllMovies(page: PageRequest, title: String): Page<MovieDTO> {
        return movieRepository.findAllMovies(title, page).map {
            MovieMapper.toMovieDTO(it)
        }
    }

    fun getMovie(id: Long): MovieDTO {
        val movie = movieRepository.findById(id).orElseThrow { EntityNotFoundException() }
        return MovieMapper.toMovieDTO(movie)
    }

    fun getMovieByTitle(title: String): OmdbMovieProjection {
        return omdbClient.getMovieByTitle(apiKey, title)
    }

    fun searchMoviesByTitle(title: String): OmdbMovieSearchProjection {
        return omdbClient.searchMoviesByTitle(apiKey, title)
    }
}