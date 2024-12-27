package com.sl.RatingService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundHandler(Exception e) {

        Map<String, Object> map = new HashMap<>();
        map.put("message", "Not Found");
        map.put("error", e.getMessage());
        map.put("success", false);
        map.put("httpStatus", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
