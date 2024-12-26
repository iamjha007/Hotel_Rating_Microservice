package com.sl.user.service.userService.exceptions;

public class NoUserFoundException extends RuntimeException{

    public NoUserFoundException(){
        super("User Not Found");
    }
}
