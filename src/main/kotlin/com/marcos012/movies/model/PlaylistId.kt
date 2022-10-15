package com.marcos012.movies.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class PlaylistId(
    @Column(name = "MOVIE_ID", nullable = false, insertable = false, updatable = false)
    private val movieId: Long,
    @Column(name = "USER_ID", nullable = false, insertable = false, updatable = false)
    private val userId: Long
) : Serializable