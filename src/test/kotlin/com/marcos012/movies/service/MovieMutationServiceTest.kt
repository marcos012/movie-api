package com.marcos012.movies.service

import com.marcos012.movies.dto.RatingDTO
import com.marcos012.movies.infra.repository.ActorRepository
import com.marcos012.movies.infra.repository.DirectorRepository
import com.marcos012.movies.infra.repository.MovieRepository
import com.marcos012.movies.mappers.MovieMapper
import com.marcos012.movies.model.Actor
import com.marcos012.movies.model.Director
import com.marcos012.movies.model.Movie
import com.marcos012.movies.providers.MovieDataProvider
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.*
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.util.ReflectionTestUtils
import java.math.BigDecimal
import java.util.*
import javax.persistence.EntityNotFoundException


@ExtendWith(MockitoExtension::class)
internal class MovieMutationServiceTest {

    @InjectMocks
    lateinit var movieService: MovieMutationService

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var actorRepository: ActorRepository

    @Mock
    lateinit var directorRepository: DirectorRepository

    @Captor
    lateinit var captor: ArgumentCaptor<Movie>

    private val movieDTO = MovieMapper.toMovieDTO(MovieDataProvider.getMovie())

    private fun mockDirectorAndActor() {
        `when`(directorRepository.findByName(anyString())).thenReturn(Optional.of(Director("Josh")))
        `when`(actorRepository.findByName(anyString())).thenReturn(Optional.of(Actor("Robert")))
    }

    private fun mockMovieSave() {
        `when`(movieRepository.save(any(Movie::class.java)))
            .thenAnswer {
                val movieMock = it.getArgument<Movie>(0)
                ReflectionTestUtils.setField(movieMock, "id", 1L)
                movieMock
            }
    }

    private fun mockMovieQuery() {
        `when`(movieRepository.findById(anyLong())).thenReturn(Optional.of(MovieDataProvider.getMovie()))
    }

    @Test
    fun `should create a movie`() {
        mockDirectorAndActor()
        mockMovieSave()

        movieService.createMovie(movieDTO)

        verify(movieRepository, times(1)).save(captor.capture())
    }

    @Test
    fun `should update a movie`() {
        val movieToUpdate = movieDTO
        movieToUpdate.title = "The avengers 2"
        mockDirectorAndActor()
        mockMovieSave()
        mockMovieQuery()

        movieService.updateMovie(1L, movieToUpdate)

        verify(movieRepository, times(1)).save(captor.capture())

        val updatedMovie = captor.value

        assertEquals(1L, updatedMovie.id)
        assertEquals("The avengers 2", updatedMovie.title)
    }

    @Test
    fun `should validate movie not found on update`() {
        assertThrows(EntityNotFoundException::class.java) {
            movieService.updateMovie(1L, movieDTO)
        }
    }

    @Test
    fun `should change personal rating`() {
        mockMovieSave()
        mockMovieQuery()

        movieService.changePersonalRating(1L, RatingDTO(BigDecimal.TEN))

        verify(movieRepository, times(1)).save(captor.capture())
    }

    @Test
    fun `should validate movie not found on change personal rating`() {
        assertThrows(EntityNotFoundException::class.java) {
            movieService.changePersonalRating(1L, RatingDTO(BigDecimal.TEN))
        }
    }
}