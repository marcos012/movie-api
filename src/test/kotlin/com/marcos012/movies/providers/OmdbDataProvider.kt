package com.marcos012.movies.providers

import com.marcos012.movies.infra.projection.OmdbMovieProjection
import com.marcos012.movies.infra.projection.OmdbMovieSearchProjection
import com.marcos012.movies.infra.projection.RatingProjection
import com.marcos012.movies.infra.projection.SearchResult
import com.marcos012.movies.model.*
import org.springframework.format.datetime.DateFormatter
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*


class OmdbDataProvider {
    companion object {
        fun getOmdbMoviesSearch(): OmdbMovieSearchProjection {
            val movie = SearchResult("the avengers", "2012", "MOVIE", "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg")

            return OmdbMovieSearchProjection(
                search = listOf(movie),
                totalResults = ""
            )


        }

        fun getOmdbMovie(): OmdbMovieProjection {
            val ratings = RatingDataProvider.getOmdbRatings()

            return OmdbMovieProjection(
                Title = "the avengers",
                Plot = "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                Genre = "Action, Adventure, Sci-Fi",
                imdbRating = BigDecimal.TEN.toString(),
                Poster = "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg",
                Type = MovieType.MOVIE.toString(),
                Actors = "Robert Downey Jr.",
                Director = "Joss Whedon",
                Runtime = "143 min",
                Ratings = ratings,
                Released = "04 May 2012",
                TotalSeasons = null,
                Production = "Marvel",
                Year = "2012"
            )
        }
    }
}