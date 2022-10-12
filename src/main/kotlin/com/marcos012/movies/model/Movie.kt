package com.marcos012.movies.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.Year
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "MOVIE")
class Movie(
    @Column(name = "TITLE", length = 100, nullable = false)
    var title: String,
    @Column(name = "PLOT")
    var plot: String,
    @Column(name = "GENRE", length = 50, nullable = false)
    var genre: String,
    @Column(length = 4, nullable = false)
    var imdb: BigDecimal,
    @Column(name = "RELEASED",nullable = false)
    var released: Date,
    @Column(name = "PRODUCER",length = 20)
    var producer: String,
    @Column(name = "POSTER")
    var poster: String,
    @Column(name = "TYPE",length = 10)
    @Enumerated(EnumType.STRING)
    var type: MovieType,
    @Column(name = "ACTORS",length = 50, nullable = true)
    var actors: String?,
    @Column(name = "DIRECTOR",length = 20, nullable = true)
    var director: String?,
    @Column(name = "RUNTIME",length = 20, nullable = true)
    var runtime: String?,
    @OneToMany(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @JoinColumn(name = "OID_MOVIE", nullable = true)
    var ratings: List<Rating>?,
    @Column(name = "TOTAL_SEASONS",length = 3, nullable = true)
    var totalSeasons: String?
) {
    @Id
    @Column(name = "OID_MOVIE",nullable = false)
    @GeneratedValue(generator = "movie_seq", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "movie_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            Parameter(name = "sequence_name", value = "SEQ_ID_MOVIE"),
            Parameter(name = "initial_value", value = "1"),
            Parameter(name = "increment_size", value = "1")])
    val id: Long? = null

    @Column(name = "CREATED_AT", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "UPDATED_AT",  nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
}