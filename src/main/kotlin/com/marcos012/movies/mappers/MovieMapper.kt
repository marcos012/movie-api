package com.marcos012.movies.mappers

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.infra.projection.MovieListProjection
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
                movie.ratings.toList(),
                movie.totalSeasons
            )
        }

        fun toMovieProjection(movie: Movie): MovieListProjection {
            return MovieListProjection(
                movie.title,
                movie.released.toString(),
                movie.type,
                movie.poster,
                movie.id
            )
        }

        fun dtoToMovie(movie: MovieDTO): Movie {
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
                movie.ratings.toMutableSet(),
                movie.totalSeasons
            )
        }
    }
}