package com.marcos012.movies.service

import com.marcos012.movies.controller.MovieQueryController
import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.dto.RatingDTO
import com.marcos012.movies.infra.repository.MovieRepository
import com.marcos012.movies.mappers.MovieMapper
import com.marcos012.movies.model.Rating
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class MovieMutationService(val movieRepository: MovieRepository) {

    fun createMovie(command: MovieDTO): MovieDTO {
        val convertedMovie = MovieMapper.toMovie(command)
        val movie = MovieMapper.toMovieDTO(movieRepository.save(convertedMovie))

        addHateoas(movie)

        return movie
    }

    fun updateMovie(id: Long, movieDTO: MovieDTO): MovieDTO {
        val movie = movieRepository.findById(id).orElseThrow { EntityNotFoundException() }

        movie.title = movieDTO.title
        movie.plot = movieDTO.plot
        movie.imdb = movieDTO.imdb
        movie.genre = movieDTO.genre
        movie.released = movieDTO.released
        movie.producer = movieDTO.producer
        movie.poster = movieDTO.poster
        movie.type = movieDTO.type
        movie.actors = movieDTO.actors
        movie.director = movieDTO.director
        movie.runtime = movieDTO.runtime
        movie.ratings = movieDTO.ratings.toMutableSet()
        movie.totalSeasons = movieDTO.totalSeasons

        val convertedMovie = MovieMapper.toMovieDTO(movieRepository.save(movie))

        addHateoas(convertedMovie)

        return convertedMovie
    }

    fun changePersonalRating(id: Long, ratingDTO: RatingDTO) {
        val movie = movieRepository.findById(id).orElseThrow { EntityNotFoundException() }
        val rating = Rating(ratingDTO.source, ratingDTO.rating.toString())

        movie.changePersonalRating(rating)

        val convertedMovie = MovieMapper.toMovieDTO(movieRepository.save(movie))

        addHateoas(convertedMovie)
    }

    private fun addHateoas(movie: MovieDTO) {
        val link = linkTo<MovieQueryController> { getMovie(movie.id) }.withSelfRel()
        movie.add(link)
    }
}