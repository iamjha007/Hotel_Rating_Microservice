package com.sl.HotelService.services;

import com.sl.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    public Hotel saveHotel(Hotel hotel);

    public Hotel getHotel(String hotelId);

    public Hotel updateHotel(Hotel hotel);

    public void deleteHotel(String hotelId);

    public List<Hotel> getAllHotels();

}
