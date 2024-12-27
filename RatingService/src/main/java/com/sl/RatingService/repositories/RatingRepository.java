package com.sl.RatingService.repositories;

import com.sl.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,String> {

    public List<Rating> findByUserId(String ratingId);

    public List<Rating> findByHotelId(String hotelId);

}
