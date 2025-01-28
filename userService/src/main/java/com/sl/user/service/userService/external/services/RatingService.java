package com.sl.user.service.userService.external.services;

import com.sl.user.service.userService.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings/users/{userId}")
    ResponseEntity<List<Rating>> getRatingsbyUserId(@PathVariable String userId);

    @PostMapping("/ratings")
    Rating saveRating(@RequestBody Rating rating);
}
