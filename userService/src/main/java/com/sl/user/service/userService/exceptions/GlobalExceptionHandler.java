package com.sl.user.service.userService.exceptions;

import com.sl.user.service.userService.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ApiResponse> handlerNoUserFoundException(NoUserFoundException ex){

        ApiResponse apiResponse = ApiResponse.builder().message(ex.getMessage()).success(true).httpStatus(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);

    }
}
