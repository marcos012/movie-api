package com.marcos012.movies.infra.repository

import com.marcos012.movies.model.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MovieRepository: JpaRepository<Movie, Long>{
    @Query(
        "SELECT * FROM MOVIE WHERE OID_MOVIE IN (SELECT OID_MOVIE FROM USER_MOVIE_LIST WHERE OID_USER = :userId)",
        nativeQuery = true
    )
    fun getUserMovieList(@Param("userId") userId: Long): Optional<List<Movie>>
}