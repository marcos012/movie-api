package com.marcos012.movies.infra.repository

import com.marcos012.movies.model.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository: JpaRepository<Movie, Long>{
}