package com.marcos012.movies.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class UserMovieId(
    @Column(name = "OID_MOVIE", nullable = false, insertable = false, updatable = false)
    private val movieId: Long,
    @Column(name = "OID_USER", nullable = false, insertable = false, updatable = false)
    private val userId: Long
) : Serializable