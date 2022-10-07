package com.marcos012.movies.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date
import javax.persistence.*

@Entity
class Movie(
    @Column(length = 100, nullable = false)
    var name: String,
    @Column(length = 4000)
    var description: String,
    @Column(length = 2, nullable = false)
    var imdb: Double,
    @Column(nullable = false)
    var releaseDate: Date,
    @Column(length = 20)
    var producer: String,
    @Column
    var imageUrl: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    val id: Long? = null
}