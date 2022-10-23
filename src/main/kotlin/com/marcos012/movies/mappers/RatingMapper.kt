package com.marcos012.movies.mappers

import com.marcos012.movies.dto.MovieRatingDTO
import com.marcos012.movies.infra.projection.RatingProjection
import com.marcos012.movies.model.Rating

class RatingMapper {
    companion object {
        fun projectionToMovieRatingDTO(rating: RatingProjection): MovieRatingDTO {
            return MovieRatingDTO(
                rating.Source,
                rating.Value
            )
        }

        fun toMovieRatingDTO(rating: Rating): MovieRatingDTO {
            return MovieRatingDTO(rating.rating, rating.source)
        }

        fun dtoToRating(movieRatingDTO: MovieRatingDTO): Rating {
            return Rating(movieRatingDTO.rating, movieRatingDTO.source)
        }
    }
}