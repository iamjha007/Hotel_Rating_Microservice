package com.sl.HotelService.exceptions;

import org.apache.logging.log4j.message.Message;

public class HotelNotFoundException extends RuntimeException{

    public HotelNotFoundException(){

        super("Hotel Not found");
    }

    public HotelNotFoundException(String message){
        super(message);
    }
}
