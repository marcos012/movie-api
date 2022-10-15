package com.marcos012.movies.infra.repository

import com.marcos012.movies.model.Movie
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MovieRepository: JpaRepository<Movie, Long>{
    @Query(
        "SELECT * FROM MOVIE WHERE OID_MOVIE IN (SELECT OID_MOVIE FROM USER_PLAYLIST WHERE OID_USER = :userId)",
        nativeQuery = true
    )
    fun getMoviesFromUser(@Param("userId") userId: Long): Optional<List<Movie>>

    @Query("SELECT m.title, m.released, m.type, m.poster FROM Movie m WHERE UPPER(m.title) LIKE CONCAT('%',UPPER(:title),'%')", nativeQuery = true)
    fun findAllMovies(@Param("title") title: String?, pageable: Pageable): Page<Movie>
}