package com.marcos012.movies.model

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import javax.persistence.*

@Entity
@Table(name = "ACTOR")
class Actor(
    @Column(name = "NAME")
    var name: String
) {
    @Id
    @Column(name = "ACTOR_ID", nullable = false)
    @GeneratedValue(generator = "actor_seq", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
        name = "actor_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            Parameter(name = "sequence_name", value = "SEQ_ACTOR_ID"),
            Parameter(name = "initial_value", value = "1"),
            Parameter(name = "increment_size", value = "1")]
    )
    var id: Long? = null

    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(name="MOVIE_ACTOR",
        joinColumns=[JoinColumn(name="ACTOR_ID")],
        inverseJoinColumns=[JoinColumn(name="MOVIE_ID")]
    )
    val movies: MutableSet<Movie> = hashSetOf()
}