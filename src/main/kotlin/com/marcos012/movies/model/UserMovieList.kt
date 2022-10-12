package com.marcos012.movies.model

import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "USER_MOVIE_LIST")
class UserMovieList(
    @EmbeddedId @NotNull val id: UserMovieId
)