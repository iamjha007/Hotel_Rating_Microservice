package com.sl.RatingService.services;

import com.sl.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    public Rating addRating(Rating rating);

    public List<Rating> getRatingByUsereId(String UserId);

    public List<Rating>  getRatingByHotelId(String ratingId);

    public List<Rating> getAllRatings();

}
