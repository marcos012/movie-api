package com.marcos012.movies.service

import com.marcos012.movies.infra.client.IOmdbClient
import com.marcos012.movies.infra.projection.MovieListProjection
import com.marcos012.movies.infra.repository.MovieRepository
import com.marcos012.movies.mappers.MovieMapper
import com.marcos012.movies.mappers.OmdbMapper
import com.marcos012.movies.providers.MovieDataProvider
import com.marcos012.movies.providers.OmdbDataProvider
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import java.util.*
import javax.persistence.EntityNotFoundException


@ExtendWith(MockitoExtension::class)
internal class MovieQueryServiceTest {

    @InjectMocks
    lateinit var movieQueryService: MovieQueryService

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var omdbClient: IOmdbClient

    @Test
    fun `should return page of movies`() {
        val mockedMovie = MovieMapper.toMovieProjection(MovieDataProvider.getMovie())
        val pageable = Pageable.ofSize(1)
        val pagedResponse: Page<MovieListProjection> = PageImpl(listOf(mockedMovie))

        `when`(movieRepository.findAllMovies("", pageable)).thenReturn(pagedResponse)

        val movies = movieQueryService.getAllMovies(PageRequest.of(0, 1), "")

        Assertions.assertEquals(pagedResponse, movies)
    }

    @Test
    fun `should return movie`() {
        val mockedMovie = MovieDataProvider.getMovie()

        `when`(movieRepository.findById(anyLong())).thenReturn(Optional.of(mockedMovie))

        val movie = movieQueryService.getMovie(1L)
        val expectedMovie = MovieMapper.toMovieDTO(mockedMovie)

        Assertions.assertEquals(expectedMovie, movie)
    }

    @Test
    fun `should validate movie not found`() {
        Assertions.assertThrows(EntityNotFoundException::class.java) {
            movieQueryService.getMovie(1L)
        }
    }

    @Test
    fun `should return movie from omdb api by title`() {
        val mockedMovie = OmdbDataProvider.getOmdbMoviesSearch()

        `when`(omdbClient.searchMoviesByTitle(anyString(), anyString())).thenReturn(mockedMovie)

        val movie = movieQueryService.searchMoviesByTitle("the avengers")
        val expectedMovie = mockedMovie.search.map {
            OmdbMapper.toMovieList(it)
        }

        Assertions.assertEquals(movie, expectedMovie)
    }

    @Test
    fun `should return list of movies from omdb`() {
        val mockedMovie = OmdbDataProvider.getOmdbMovie()

        `when`(omdbClient.getMovieByTitle(anyString(), anyString())).thenReturn(mockedMovie)

        val movie = movieQueryService.getMovieByTitle("the avengers")
        val expectedMovie = OmdbMapper.toMovieDTO(mockedMovie)

        Assertions.assertEquals(movie, expectedMovie)
    }
}