package com.marcos012.movies.service

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.dto.RatingDTO

interface IMovieMutationService {
    fun createMovie(command: MovieDTO): MovieDTO
    fun updateMovie(id: Long, movieDTO: MovieDTO): MovieDTO
    fun changePersonalRating(id: Long, ratingDTO: RatingDTO): MovieDTO
}