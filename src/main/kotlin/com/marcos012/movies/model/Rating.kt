package com.marcos012.movies.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import javax.persistence.*

@Entity
@Table(name = "RATING")
class Rating(
    @Column(name = "SOURCE")
    var source: String,
    @Column(name = "RATING")
    @JsonProperty("value")
    var rating: String
) {
    @Id
    @Column(name = "OID_RATING", nullable = false)
    @GeneratedValue(generator = "movie_rating_seq", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "movie_rating_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            Parameter(name = "sequence_name", value = "SEQ_ID_RATING"),
            Parameter(name = "initial_value", value = "1"),
            Parameter(name = "increment_size", value = "1")])
    var id: Long? = null

    @Column(name = "OID_MOVIE", insertable = false, updatable = false)
    private val movieId: Long? = null
}