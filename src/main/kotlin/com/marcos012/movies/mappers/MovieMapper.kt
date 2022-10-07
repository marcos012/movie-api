package com.marcos012.movies.mappers

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.model.Movie

class MovieMapper {
    companion object {
        fun toMovieDTO(movie: Movie): MovieDTO {
            return MovieDTO(
                movie.id!!,
                movie.name,
                movie.description,
                movie.imdb,
                movie.releaseDate,
                movie.producer,
                movie.imageUrl
            )
        }

        fun toMovie(movie: MovieDTO): Movie {
            return Movie(
                movie.name,
                movie.description,
                movie.imdb,
                movie.releaseDate,
                movie.producer,
                movie.imageUrl
            )
        }
    }
}