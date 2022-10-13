package com.marcos012.movies.mappers

import com.marcos012.movies.infra.projection.RatingProjection
import com.marcos012.movies.model.Rating

class RatingMapper {
    companion object {
        fun toRating(rating: RatingProjection): Rating {
            return Rating(
                rating.Source,
                rating.Value
            )
        }
    }
}