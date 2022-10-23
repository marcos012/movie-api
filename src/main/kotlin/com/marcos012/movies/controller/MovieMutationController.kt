package com.marcos012.movies.controller

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.dto.RatingDTO
import com.marcos012.movies.infra.repository.MovieRepository
import com.marcos012.movies.service.MovieMutationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/movies")
class MovieMutationController(val movieService: MovieMutationService) {

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

    @PatchMapping("{id}")
    fun changePersonalRatingToMovie(
        @PathVariable id: Long,
        @RequestBody ratingDTO: RatingDTO
    ): ResponseEntity<MovieDTO> {
        val movie = movieService.changePersonalRating(id, ratingDTO)
        return ResponseEntity(movie, HttpStatus.OK)
    }

    @DeleteMapping("{id}")
    fun deleteMovie(@PathVariable id: Long): ResponseEntity<Void> {
        movieService.deleteMovie(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}