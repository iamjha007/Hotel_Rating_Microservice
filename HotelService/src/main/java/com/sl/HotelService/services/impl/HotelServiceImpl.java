package com.sl.HotelService.services.impl;

import com.sl.HotelService.entities.Hotel;
import com.sl.HotelService.exceptions.HotelNotFoundException;
import com.sl.HotelService.repositories.HotelRepository;
import com.sl.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel saveHotel(Hotel hotel) {

        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new HotelNotFoundException());
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        return null;
    }

    @Override
    public void deleteHotel(String hotelId) {

    }

    @Override
    public List<Hotel> getAllHotels() {

        return hotelRepository.findAll();
    }
}
