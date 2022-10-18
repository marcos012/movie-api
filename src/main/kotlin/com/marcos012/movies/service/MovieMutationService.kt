package com.marcos012.movies.service

import com.marcos012.movies.controller.MovieQueryController
import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.dto.RatingDTO
import com.marcos012.movies.infra.repository.ActorRepository
import com.marcos012.movies.infra.repository.DirectorRepository
import com.marcos012.movies.infra.repository.MovieRepository
import com.marcos012.movies.mappers.ActorMapper
import com.marcos012.movies.mappers.MovieMapper
import com.marcos012.movies.model.Actor
import com.marcos012.movies.model.Director
import com.marcos012.movies.model.Rating
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

@Service
class MovieMutationService(
    val movieRepository: MovieRepository,
    val actorRepository: ActorRepository,
    val directorRepository: DirectorRepository
) : IMovieMutationService {
    override fun createMovie(command: MovieDTO): MovieDTO {
        val movie = MovieMapper.dtoToMovie(command)

        movie.director = getDirector(command.director)
        getActors(command.actors).forEach { movie.addActor(it) }

        val movieDTO = MovieMapper.toMovieDTO(movieRepository.save(movie))

        addHateoas(movieDTO)

        return movieDTO
    }

    override fun updateMovie(id: Long, movieDTO: MovieDTO): MovieDTO {
        val movie = movieRepository.findById(id).orElseThrow { EntityNotFoundException() }

        movie.title = movieDTO.title
        movie.plot = movieDTO.plot
        movie.imdb = movieDTO.imdb
        movie.genre = movieDTO.genre
        movie.released = movieDTO.released
        movie.producer = movieDTO.producer
        movie.poster = movieDTO.poster
        movie.type = movieDTO.type
        movie.director = getDirector(movieDTO.director)
        movie.runtime = movieDTO.runtime
        movie.ratings = movieDTO.ratings.toMutableSet()
        movie.totalSeasons = movieDTO.totalSeasons
        movie.updatedAt = LocalDateTime.now()

        getActors(movieDTO.actors).forEach { movie.addActor(it) }
        addHateoas(movieDTO)

        movieRepository.save(movie)

        return movieDTO
    }

    override fun changePersonalRating(id: Long, ratingDTO: RatingDTO): MovieDTO {
        val movie = movieRepository.findById(id).orElseThrow { EntityNotFoundException() }
        val rating = Rating("personal", ratingDTO.rating.toString())

        movie.changePersonalRating(rating)

        val convertedMovie = MovieMapper.toMovieDTO(movieRepository.save(movie))

        addHateoas(convertedMovie)

        return convertedMovie
    }

    fun getActors(actors: String?): Set<Actor> {
        return ActorMapper
            .toActor(actors)
            .map { actorRepository.findByName(it.name).orElse(Actor(it.name)) }
            .toSet()
    }

    fun getDirector(director: String?): Director? {
        return if (director != null)
            directorRepository.findByName(director).orElse(Director(director))
        else null
    }

    private fun addHateoas(movie: MovieDTO) {
        val link = linkTo<MovieQueryController> { getMovie(movie.id) }.withSelfRel()
        movie.add(link)
    }
}