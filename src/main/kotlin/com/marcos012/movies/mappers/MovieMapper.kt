package com.marcos012.movies.mappers

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.infra.projection.MovieListProjection
import com.marcos012.movies.model.Director
import com.marcos012.movies.model.Movie

class MovieMapper {
    companion object {
        fun toMovieDTO(movie: Movie): MovieDTO {
            val actors = movie.actors.joinToString { it.name }

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
                actors,
                movie.director?.name,
                movie.runtime,
                movie.ratings.toList(),
                movie.totalSeasons
            )
        }

        fun toMovieProjection(movie: Movie): MovieListProjection {
            return MovieListProjection(
                movie.title,
                movie.released,
                movie.type,
                movie.poster,
                movie.id
            )
        }

        fun dtoToMovie(movie: MovieDTO): Movie {
            return Movie(
                title = movie.title,
                plot = movie.plot,
                genre = movie.genre,
                imdb = movie.imdb,
                released = movie.released,
                producer = movie.producer,
                poster = movie.poster,
                type = movie.type,
                runtime = movie.runtime,
                totalSeasons = movie.totalSeasons,
                ratings = movie.ratings.toMutableSet(),
            )
        }
    }
}