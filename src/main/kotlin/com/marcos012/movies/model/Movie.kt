package com.marcos012.movies.model

import com.marcos012.movies.handlers.validation.DomainBusinessException
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
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
    @Column(name = "RELEASED", nullable = false)
    var released: Date,
    @Column(name = "PRODUCER", length = 20)
    var producer: String,
    @Column(name = "POSTER")
    var poster: String,
    @Column(name = "TYPE", length = 10)
    @Enumerated(EnumType.STRING)
    var type: MovieType,
    @Column(name = "RUNTIME", length = 20, nullable = true)
    var runtime: String?,
    @Column(name = "TOTAL_SEASONS", length = 3, nullable = true)
    var totalSeasons: String?,

    @OneToMany(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @JoinColumn(name = "MOVIE_ID", nullable = true)
    var ratings: MutableSet<Rating> = hashSetOf(),

    @ManyToMany(mappedBy = "movies", cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    var actors: MutableSet<Actor> = hashSetOf(),

    @ManyToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @JoinColumn(name = "DIRECTOR_ID")
    var director: Director? = null
) : Serializable {
    @Id
    @Column(name = "MOVIE_ID", nullable = false)
    @GeneratedValue(generator = "movie_seq", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
        name = "movie_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            Parameter(name = "sequence_name", value = "SEQ_ID_MOVIE"),
            Parameter(name = "initial_value", value = "1"),
            Parameter(name = "increment_size", value = "1")]
    )
    var id: Long? = null

    @Column(name = "CREATED_AT", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "UPDATED_AT", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()

    fun changePersonalRating(rating: Rating) {
        if (rating.source != "personal")
            throw DomainBusinessException("Source should be: personal")

        val personalRating = this.ratings.find { it.source == "personal" }

        if (personalRating != null)
            personalRating.rating = rating.rating
        else
            this.ratings.add(rating)
    }

    @PreRemove
    fun removeActorsFromMovie() {
        actors.forEach {
            it.movies.remove(this)
        }
    }

    fun addActor(actor: Actor) {
        this.actors.add(actor)
        actor.movies.add(this)
    }
}