package com.marcos012.movies.infra.repository

import com.marcos012.movies.model.UserMovieId
import com.marcos012.movies.model.UserMovieList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserMovieRepository : JpaRepository<UserMovieList, UserMovieId> {
}