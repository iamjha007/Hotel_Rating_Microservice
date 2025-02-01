package com.sl.user.service.userService.controller;

import com.sl.user.service.userService.entity.Rating;
import com.sl.user.service.userService.entity.User;
import com.sl.user.service.userService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    int count=0;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        User user1=userService.saveUser(user);
        Thread.currentThread().getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "ratingHotelLimiter", fallbackMethod = "ratingHotelFallback")
    //http://localhost:8081/actuator/health
    public ResponseEntity<User> getUser(@PathVariable String userId){

        log.info("{}",count++);
        log.info("{}",Thread.currentThread().getName());
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // fallback method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception e) {

        log.info("Fallback method is called. Exception: {}", e.getMessage());
        User user = User.builder().name("Fallback User").build();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping
//    @CircuitBreaker(name = "ratingHotelBreakerAllUser", fallbackMethod = "ratingHotelFallbackAllUser")
//    @Retry(name = "ratingHotelRetryAllUser", fallbackMethod = "ratingHotelFallbackAllUser")
    @RateLimiter(name = "ratingHotelLimiterAllUser", fallbackMethod = "ratingHotelFallbackAllUser")
    public ResponseEntity<List<User>> getAllUser(){

        log.info("{}",count++);
        log.info("{}",Thread.currentThread().getName());
        return ResponseEntity.ok(userService.getAllUser());
    }

    public ResponseEntity<List<User>> ratingHotelFallbackAllUser(Exception e) {

        log.info("Fallback method is called. Exception: {}", e.getMessage());
        User user = User.builder().name("Fallback User").build();
        return ResponseEntity.status(HttpStatus.OK).body(List.of(user));
    }

    @PostMapping("/publishrating")
    public ResponseEntity<Rating> publishRating(@RequestBody Rating rating){

        return ResponseEntity.ok(userService.publishRating(rating));
    }


}
