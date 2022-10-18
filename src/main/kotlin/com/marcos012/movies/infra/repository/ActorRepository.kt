package com.marcos012.movies.infra.repository

import com.marcos012.movies.model.Actor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ActorRepository: JpaRepository<Actor, Long>{
    @Query(
        "SELECT * FROM ACTOR WHERE NAME = :name",
        nativeQuery = true
    )
    fun findByName(@Param("name") name: String): Optional<Actor>
}