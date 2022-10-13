package com.marcos012.movies.mappers

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.model.Movie

class MovieMapper {
    companion object {
        fun toMovieDTO(movie: Movie): MovieDTO {
            return MovieDTO(
                movie.id!!,
                movie.title,
                movie.plot,
                movie.genre,
                movie.imdb,
                movie.released,
                movie.producer,
                movie.poster,
                movie.type,
                movie.actors,
                movie.director,
                movie.runtime,
                movie.ratings,
                movie.totalSeasons
            )
        }

        fun toMovie(movie: MovieDTO): Movie {
            return Movie(
                movie.title,
                movie.plot,
                movie.genre,
                movie.imdb,
                movie.released,
                movie.producer,
                movie.poster,
                movie.type,
                movie.actors,
                movie.director,
                movie.runtime,
                movie.ratings,
                movie.totalSeasons
            )
        }
    }
}