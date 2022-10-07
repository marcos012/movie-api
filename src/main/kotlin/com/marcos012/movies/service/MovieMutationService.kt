package com.marcos012.movies.service

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.mappers.MovieMapper
import com.marcos012.movies.infra.repository.MovieRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class MovieMutationService(private val movieRepository: MovieRepository) {

    fun createMovie(movieDTO: MovieDTO): Long? {
        val movie = MovieMapper.toMovie(movieDTO)
        return movieRepository.save(movie).id
    }

    fun updateMovie(id: Long, movieDTO: MovieDTO): Long? {
        val movie = movieRepository.findById(id).orElseThrow { EntityNotFoundException() }

        movie.name = movieDTO.name
        movie.imdb = movieDTO.imdb
        movie.description = movieDTO.description
        movie.producer = movieDTO.producer
        movie.releaseDate = movieDTO.releaseDate
        movie.imageUrl = movieDTO.imageUrl

        return movieRepository.save(movie).id
    }
}