package com.sl.RatingService.services.impl;

import com.sl.RatingService.entities.Rating;
import com.sl.RatingService.exceptions.RatingNotFoundException;
import com.sl.RatingService.repositories.RatingRepository;
import com.sl.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {


    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating addRating(Rating rating) {

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatingByUsereId(String ratingId) {
        return ratingRepository.findByUserId(ratingId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String HotelId) {
        return ratingRepository.findByHotelId(HotelId);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
}
