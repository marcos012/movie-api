package com.marcos012.movies.service

import com.marcos012.movies.infra.repository.MovieRepository
import com.marcos012.movies.infra.repository.PlaylistRepository
import com.marcos012.movies.mappers.MovieMapper
import com.marcos012.movies.model.Playlist
import com.marcos012.movies.model.PlaylistId
import com.marcos012.movies.providers.MovieDataProvider
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.util.ReflectionTestUtils
import java.util.*
import javax.persistence.EntityNotFoundException


@ExtendWith(MockitoExtension::class)
internal class PlaylistServiceTest {

    @InjectMocks
    lateinit var playlistService: PlaylistService

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var playlistRepository: PlaylistRepository

    @Captor
    lateinit var captor: ArgumentCaptor<Playlist>

    private fun mockSave() {
        `when`(playlistRepository.save(any())).thenAnswer {
            val playlist = it.getArgument<Playlist>(0)
            ReflectionTestUtils.setField(playlist, "id", PlaylistId(1L, 1L))
            playlist
        }
    }

    private fun mockFindMovie() {
        `when`(movieRepository.findById(anyLong())).thenReturn(Optional.of(MovieDataProvider.getMovie()))
    }

    @Test
    fun `should return movies from user playlist`() {
        val movies = listOf(MovieDataProvider.getMovie())
        `when`(movieRepository.getMoviesFromUser(anyLong())).thenReturn(Optional.of(movies))

        val playlistMovies = playlistService.getMovies(1L)
        val expectedMovies = movies.map { MovieMapper.toMovieDTO(it) }

        Assertions.assertEquals(1, playlistMovies.size)
        Assertions.assertEquals(expectedMovies, playlistMovies)

    }

    @Test
    fun `should add movie to playlist`() {
        mockFindMovie()
        mockSave()

        playlistService.addMovie(1L, 1L)

        verify(playlistRepository, times(1)).save(captor.capture())
    }

    @Test
    fun `should remove movies from playlist`() {
        mockFindMovie()

        playlistService.removeMovie(1L, 1L)

        verify(playlistRepository, times(1)).deleteById(any())
    }

    @Test
    fun `should validate movie movie not found on add`() {
        Assertions.assertThrows(EntityNotFoundException::class.java) {
            playlistService.addMovie(1L, 1L)
        }
    }

    @Test
    fun `should validate movie movie not found on delete`() {
        Assertions.assertThrows(EntityNotFoundException::class.java) {
            playlistService.removeMovie(1L, 1L)
        }
    }
}