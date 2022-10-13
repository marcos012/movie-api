package com.marcos012.movies.service

import com.marcos012.movies.dto.MovieDTO
import com.marcos012.movies.infra.repository.MovieRepository
import com.marcos012.movies.infra.repository.PlaylistRepository
import com.marcos012.movies.mappers.MovieMapper
import com.marcos012.movies.model.Playlist
import com.marcos012.movies.model.PlaylistId
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class PlaylistService(
    val movieRepository: MovieRepository,
    val playlistRepository: PlaylistRepository
) : IPlaylistService {
    override fun getMovies(userId: Long): List<MovieDTO> {
        val movieList = movieRepository.getMoviesFromUser(userId).orElseThrow { EntityNotFoundException() }
        return movieList.map { MovieMapper.toMovieDTO(it) }
    }

    override fun addMovie(movieId: Long, userId: Long): MovieDTO {
        val movie = movieRepository.findById(movieId).orElseThrow { EntityNotFoundException() }
        val playlist = Playlist(PlaylistId(movieId, userId))

        playlistRepository.save(playlist)

        return MovieMapper.toMovieDTO(movie)
    }

    override fun removeMovie(movieId: Long, userId: Long) {
        movieRepository.findById(movieId).orElseThrow { EntityNotFoundException() }
        playlistRepository.deleteById(PlaylistId(movieId, userId))
    }
}