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
    var rating: String
) {
    @Id
    @Column(name = "RATING_ID", nullable = false)
    @GeneratedValue(generator = "movie_rating_seq", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "movie_rating_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            Parameter(name = "sequence_name", value = "SEQ_ID_RATING"),
            Parameter(name = "initial_value", value = "1"),
            Parameter(name = "increment_size", value = "1")])
    var id: Long? = null

    @Column(name = "MOVIE_ID", insertable = false, updatable = false)
    private val movieId: Long? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rating

        if (source != other.source) return false
        if (rating != other.rating) return false
        if (id != other.id) return false
        if (movieId != other.movieId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = source.hashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (movieId?.hashCode() ?: 0)
        return result
    }


}