package com.marcos012.movies.mappers

import com.marcos012.movies.model.Actor

class ActorMapper {
    companion object {
        fun toActor(actors: String?): Set<Actor> {
            return when {
                actors == null -> emptySet()
                actors.contains(",") -> actors
                    .split(", ")
                    .map { Actor(it) }
                    .toSet()
                else -> setOf(Actor(actors))
            }


        }
    }
}