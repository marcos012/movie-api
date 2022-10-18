package com.marcos012.movies.infra.repository

import com.marcos012.movies.model.Actor
import com.marcos012.movies.model.Director
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DirectorRepository: JpaRepository<Director, Long>{
    @Query(
        "SELECT * FROM DIRECTOR WHERE NAME = :name",
        nativeQuery = true
    )
    fun findByName(@Param("name") name: String): Optional<Director>
}