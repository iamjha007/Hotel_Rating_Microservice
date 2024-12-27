package com.sl.RatingService.exceptions;


public class RatingNotFoundException extends RuntimeException{

    public RatingNotFoundException() {
        super("Rating Not found");
    }

    public RatingNotFoundException(String message) {
        super(message);
    }
}
