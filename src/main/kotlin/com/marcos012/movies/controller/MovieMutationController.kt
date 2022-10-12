package com.marcos012.movies.controller

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.infra.repository.MovieRepository
import com.marcos012.movies.service.MovieMutationService
import com.marcos012.movies.service.MovieQueryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/movies")
class MovieMutationController(val movieService: MovieMutationService, val movieRepository: MovieRepository) {

    @PostMapping
    fun createMovie(@RequestBody movieDTO: MovieDTO): ResponseEntity<MovieDTO> {
        val movie = movieService.createMovie(movieDTO)
        return ResponseEntity(movie, HttpStatus.CREATED)
    }

    @PutMapping("{id}")
    fun updateMovie(@PathVariable id: Long, @RequestBody movie: MovieDTO): ResponseEntity<MovieDTO> {
        val updatedMovie = movieService.updateMovie(id, movie)
        return ResponseEntity(updatedMovie, HttpStatus.OK)
    }

    @DeleteMapping("{id}")
    fun deleteMovie(@PathVariable id: Long): ResponseEntity<Void> {
        movieRepository.deleteById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}