package com.marcos012.movies.infra.repository

import com.marcos012.movies.infra.projection.MovieListProjection
import com.marcos012.movies.model.Movie
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MovieRepository : JpaRepository<Movie, Long> {
    @Query(
        "SELECT * FROM MOVIE WHERE MOVIE_ID IN (SELECT MOVIE_ID FROM USER_PLAYLIST WHERE USER_ID = :userId)",
        nativeQuery = true
    )
    fun getMoviesFromUser(@Param("userId") userId: Long): Optional<List<Movie>>

    @Query(
        "SELECT new com.marcos012.movies.infra.projection.MovieListProjection(" +
                "m.title, " +
                "m.released, " +
                "m.type, " +
                "m.poster, " +
                "m.id) " +
                "FROM Movie m " +
                "WHERE UPPER(m.title) LIKE CONCAT('%',UPPER(:title),'%')"
    )
    fun findAllMovies(@Param("title") title: String?, pageable: Pageable): Page<MovieListProjection>

//    @Query("SELECT * FROM Movie m " +
//            "WHERE m.id = :id " +
//            "LEFT JOIN MovieActor ma ON ma.movieId = :id " +
//            "LEFT JOIN Actor a ON a.id = ma.actorId",
//        nativeQuery = true
//    )
//    override fun findById(@Param("id") id: Long): Optional<Movie>
}