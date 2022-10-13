package com.marcos012.movies.controller

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.infra.repository.UserMovieRepository
import com.marcos012.movies.service.UserMovieListService
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/users")
class UserController(
    val userMovieRepository: UserMovieRepository,
    val userMovieListService: UserMovieListService
) {

    @PostMapping("movie-list/{movieId}")
    fun createUserMovieList(
        @PathVariable movieId: Long,
        @RequestHeader(AUTHORIZATION) userId: Long,
    ): ResponseEntity<Void> {
        userMovieListService.createUserMovieList(movieId, userId)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("movie-list")
    fun getMovieList(@RequestHeader(AUTHORIZATION) userId: Long): ResponseEntity<List<MovieDTO>> {
        val movies = userMovieListService.getUserMovieList(userId)
        return ResponseEntity(movies, HttpStatus.OK)
    }
}