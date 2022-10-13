package com.marcos012.movies.service

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.infra.repository.MovieRepository
import com.marcos012.movies.infra.repository.UserMovieRepository
import com.marcos012.movies.mappers.MovieMapper
import com.marcos012.movies.model.UserMovieId
import com.marcos012.movies.model.UserMovieList
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class UserMovieListService(val movieRepository: MovieRepository, val userMovieRepository: UserMovieRepository) {
    fun getUserMovieList(userId: Long): List<MovieDTO> {
        val movieList = movieRepository.getUserMovieList(userId).orElseThrow { EntityNotFoundException() }

        return movieList.map { MovieMapper.toMovieDTO(it) }
    }

    fun createUserMovieList(movieId: Long, userId: Long) {
        movieRepository.findById(movieId).orElseThrow { EntityNotFoundException() }

        val userMovie = UserMovieList(UserMovieId(movieId, userId))
        userMovieRepository.save(userMovie)
    }
}