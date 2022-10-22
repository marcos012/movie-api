package com.marcos012.movies.providers

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.mappers.RatingMapper
import com.marcos012.movies.model.*
import java.math.BigDecimal
import java.text.SimpleDateFormat


class MovieRequestProvider {
    companion object {
        fun create(): MovieDTO {
            val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-04-04 00:00:00")
            val ratings = RatingDataProvider
                .getRatings()
                .map { RatingMapper.toMovieRatingDTO(it) }
                .toList()


            return MovieDTO(
                title = "the avengers",
                plot = "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                genre = "Action, Adventure, Sci-Fi",
                imdb = BigDecimal.TEN,
                producer = "Marvel MCU",
                poster = "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg",
                type = MovieType.MOVIE,
                actors = "Robert Downey Jr., Chris Evans",
                director = "Joss Whedon",
                runtime = "143 min",
                ratings = ratings,
                released = date,
                totalSeasons = null,
                id = null,
            )
        }
    }
}