package com.marcos012.movies.providers

import com.marcos012.movies.model.*
import java.math.BigDecimal
import java.text.SimpleDateFormat


class MovieDataProvider {
    companion object {
        fun getMovie(): Movie {
            val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-04-04 00:00:00")
            val actor = Actor("Robert Downey Jr.")
            val director = Director("Joss Whedon")
            val ratings = RatingDataProvider.getRatings()

            val movie = Movie(
                title = "the avengers",
                plot = "Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.",
                genre = "Action, Adventure, Sci-Fi",
                imdb = BigDecimal.TEN,
                producer = "Marvel MCU",
                poster = "https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg",
                type = MovieType.MOVIE,
                actors = mutableSetOf(actor),
                director = director,
                runtime = "143 min",
                ratings = ratings,
                released = date,
                totalSeasons = null
            )

            movie.id = 1L

            return movie

        }

    }
}