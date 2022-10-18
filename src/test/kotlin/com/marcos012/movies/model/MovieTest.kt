package com.marcos012.movies.model

import com.marcos012.movies.handlers.validation.DomainBusinessException
import com.marcos012.movies.providers.MovieDataProvider
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class MovieTest {
    @Test
    fun `should add personal rating to movie`() {
        val movie = MovieDataProvider.getMovie()
        val rating = Rating("personal", "8.5")

        movie.changePersonalRating(rating)

        assert(movie.ratings.contains(rating))
    }

    @Test
    fun `should throw error if rating is not personal`() {
        val movie = MovieDataProvider.getMovie()
        val rating = Rating("not personal", "8.5")

        assertThrows(DomainBusinessException::class.java) {
            movie.changePersonalRating(rating)
        }
    }

    @Test
    fun `should add actor to movie`() {
        val movie = MovieDataProvider.getMovie()
        val actor = Actor("Vin Diesel")
        movie.addActor(actor)

        assert(movie.actors.contains(actor))
    }
}