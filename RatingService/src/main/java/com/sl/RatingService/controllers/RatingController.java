package com.sl.RatingService.controllers;


import com.sl.RatingService.entities.Rating;
import com.sl.RatingService.services.impl.RatingServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingServiceImpl ratingService;

    @PostMapping
    private ResponseEntity<Rating> addRating(@RequestBody Rating rating) {

        return ResponseEntity.ok(ratingService.addRating(rating));

    }

    @GetMapping("/users/{userId}")
    private ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {

        return ResponseEntity.ok(ratingService.getRatingByUsereId(userId));

    }

    @GetMapping("/hotels/{hotelId}")
    private ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {

        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));

    }

    @GetMapping
    private ResponseEntity<List<Rating>> getAllRatings() {

        return ResponseEntity.ok(ratingService.getAllRatings());

    }
}
