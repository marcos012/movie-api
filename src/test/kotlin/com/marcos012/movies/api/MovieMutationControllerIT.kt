package com.marcos012.movies.api

import com.marcos012.movies.dto.RatingDTO
import com.marcos012.movies.providers.MovieRequestProvider
import com.marcos012.movies.providers.SqlProvider
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal


internal class MovieMutationControllerIT : BaseControllerIT() {

    @Test
    @SqlGroup(
        Sql(SqlProvider.MOVIE_DELETE, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    )
    fun `should create a movie`() {
        val movieRequest = MovieRequestProvider.create()

        mvc.perform(
            MockMvcRequestBuilders.post("/v1/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(movieRequest))
        ).andExpect(status().isCreated)
    }

    @Test
    @SqlGroup(
        Sql(SqlProvider.MOVIE_INSERT),
        Sql(SqlProvider.MOVIE_DELETE, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    )
    fun `should update movie`() {
        val movieRequest = MovieRequestProvider.create()
        movieRequest.id = 1L
        movieRequest.title = "The Avengers"
        movieRequest.ratings = emptyList()

        mvc.perform(
            MockMvcRequestBuilders.put("/v1/movies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(movieRequest))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title", equalTo("The Avengers")))
    }

    @Test
    @SqlGroup(
        Sql(SqlProvider.MOVIE_INSERT),
        Sql(SqlProvider.MOVIE_DELETE, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    )
    fun `should delete movie`() {
        val movieRequest = MovieRequestProvider.create()
        movieRequest.id = 1L

        mvc.perform(
            MockMvcRequestBuilders.delete("/v1/movies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(movieRequest))
        ).andExpect(status().isNoContent)
    }

    @Test
    @SqlGroup(
        Sql(SqlProvider.MOVIE_INSERT),
        Sql(SqlProvider.MOVIE_DELETE, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    )
    fun `should change personal rating from movie`() {
        mvc.perform(
            MockMvcRequestBuilders.patch("/v1/movies/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(RatingDTO(BigDecimal.TEN)))
        ).andExpect(status().isOk)
    }

}