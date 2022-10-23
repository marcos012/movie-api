package com.marcos012.movies.controller

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.service.PlaylistService
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/playlist")
class PlaylistController(val playlistService: PlaylistService) {

    @PostMapping("{movieId}")
    fun addMovieToPlaylist(
        @PathVariable movieId: Long,
        @RequestHeader(AUTHORIZATION) userId: Long,
    ): ResponseEntity<MovieDTO> {
        val addedMovie = playlistService.addMovie(movieId, userId)
        return ResponseEntity(addedMovie, HttpStatus.OK)
    }

    @DeleteMapping("{movieId}")
    fun removeMovieToPlaylist(
        @PathVariable movieId: Long,
        @RequestHeader(AUTHORIZATION) userId: Long,
    ): ResponseEntity<Void> {
        playlistService.removeMovie(movieId, userId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping
    fun getMovies(@RequestHeader(AUTHORIZATION) userId: Long): ResponseEntity<List<MovieDTO>> {
        val movies = playlistService.getMovies(userId)
        return ResponseEntity(movies, HttpStatus.OK)
    }
}