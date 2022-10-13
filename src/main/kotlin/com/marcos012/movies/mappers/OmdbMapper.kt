package com.marcos012.movies.mappers

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.infra.projection.MovieListProjection
import com.marcos012.movies.infra.projection.OmdbMovieProjection
import com.marcos012.movies.infra.projection.SearchResult
import org.springframework.format.datetime.DateFormatter
import java.math.BigDecimal
import java.util.*

class OmdbMapper {
    companion object {
        fun toMovieList(omdbProjection: SearchResult): MovieListProjection {
            return MovieListProjection(
                omdbProjection.Title,
                omdbProjection.Year,
                enumValueOf(omdbProjection.Type.uppercase()),
                omdbProjection.Poster,
                null,
            )
        }

        fun toMovieDTO(omdbProjection: OmdbMovieProjection): MovieDTO {
            val parsedDate = omdbProjection.Released.split(" ").joinToString("-")
            val movieDate = DateFormatter("dd-MMM-yyyy").parse(parsedDate, Locale.US)

            return MovieDTO(
                1,
                omdbProjection.Title,
                omdbProjection.Plot,
                omdbProjection.Genre,
                BigDecimal(omdbProjection.imdbRating),
                movieDate,
                omdbProjection.Production,
                omdbProjection.Poster,
                enumValueOf(omdbProjection.Type.uppercase()),
                omdbProjection.Actors,
                omdbProjection.Director,
                omdbProjection.Runtime,
                omdbProjection.Ratings.map { RatingMapper.toRating(it) },
                omdbProjection.TotalSeasons,

            )
        }
    }
}