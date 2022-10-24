package com.marcos012.movies.api

import com.marcos012.movies.providers.SqlProvider
import org.hamcrest.Matchers.equalTo
import org.hamcrest.collection.IsCollectionWithSize
import org.junit.jupiter.api.Test
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class MovieQueryControllerIT : BaseControllerIT() {

    @Test
    @SqlGroup(
        Sql(SqlProvider.MOVIE_INSERT),
        Sql(SqlProvider.MOVIE_DELETE, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    )
    fun `should list movies`() {
        mvc.perform(
            MockMvcRequestBuilders.get("/v1/movies")
                .param("title", "")
                .param("page", "0")
                .param("limit", "20")
                .param("sort", "ASC")
                .param("orderBy", "title")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content", IsCollectionWithSize.hasSize<Any>(1)))
            .andExpect(jsonPath("$.content.[0].id", equalTo(1)))
            .andExpect(jsonPath("$.content.[0].title", equalTo("Avengers")))
            .andExpect(jsonPath("$.content.[0].type", equalTo("MOVIE")))
            .andExpect(jsonPath("$.content.[0].poster", equalTo("poster.png")))
    }

    @Test
    @SqlGroup(
        Sql(SqlProvider.MOVIE_INSERT),
        Sql(SqlProvider.MOVIE_DELETE, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    )
    fun `should return movie`() {
        mvc.perform(MockMvcRequestBuilders.get("/v1/movies/{id}", 1))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id", equalTo(1)))
            .andExpect(jsonPath("$.title", equalTo("Avengers")))
            .andExpect(jsonPath("$.actors", equalTo("Chris Evans, Robert Downey Jr.")))
            .andExpect(jsonPath("$.director", equalTo("Joss Whedon")))
    }
}
