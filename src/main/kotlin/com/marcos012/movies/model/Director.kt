package com.marcos012.movies.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import javax.persistence.*

@Entity
@Table(name = "DIRECTOR")
class Director(
    @Column(name = "NAME")
    var name: String
) {
    @Id
    @Column(name = "DIRECTOR_ID", nullable  = false)
    @GeneratedValue(generator = "director_seq", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "director_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = [
            Parameter(name = "sequence_name", value = "SEQ_DIRECTOR_ID"),
            Parameter(name = "initial_value", value = "1"),
            Parameter(name = "increment_size", value = "1")])
    var id: Long? = null
}