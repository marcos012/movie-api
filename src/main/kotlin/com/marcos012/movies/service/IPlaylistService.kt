package com.marcos012.movies.service

import com.marcos012.movies.dto.MovieDTO

interface IPlaylistService {
    fun getMovies(userId: Long): List<MovieDTO>
    fun addMovie(movieId: Long, userId: Long): MovieDTO
    fun removeMovie(movieId: Long, userId: Long)
}