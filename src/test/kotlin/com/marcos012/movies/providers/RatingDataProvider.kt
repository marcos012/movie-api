package com.marcos012.movies.providers

import com.marcos012.movies.infra.projection.RatingProjection
import com.marcos012.movies.model.*
import java.math.BigDecimal
import java.text.SimpleDateFormat


class RatingDataProvider {
    companion object {
        fun getRatings(): MutableSet<Rating> {
            return mutableSetOf(
                Rating("Rotten Tomatoes", "91%"),
                Rating("Metacritic", "69/100"),
                Rating("Internet Movie Database", "8.0/10"),
            )
        }

        fun getOmdbRatings(): List<RatingProjection> {
            return listOf(
                RatingProjection("Rotten Tomatoes", "91%"),
                RatingProjection("Metacritic", "69/100"),
                RatingProjection("Internet Movie Database", "8.0/10"),
            )
        }
    }
}