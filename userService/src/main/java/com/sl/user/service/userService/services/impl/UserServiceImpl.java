package com.sl.user.service.userService.services.impl;

import com.sl.user.service.userService.Repositories.UserRepository;
import com.sl.user.service.userService.entity.Hotel;
import com.sl.user.service.userService.entity.Rating;
import com.sl.user.service.userService.entity.User;
import com.sl.user.service.userService.exceptions.NoUserFoundException;
import com.sl.user.service.userService.external.services.HotelService;
import com.sl.user.service.userService.external.services.RatingService;
import com.sl.user.service.userService.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;
    @Override
    public User saveUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        List<User> users = userRepository.findAll();

        for(User user : users){
            ResponseEntity<Rating[]> ratingListOfUser = restTemplate.getForEntity("http://RATINGSERVICE/ratings/users/"+user.getId(), Rating[].class);
            log.info("{}",ratingListOfUser.getBody());
            user.setRatings(List.of(ratingListOfUser.getBody()));

            List<Rating> ratingList = user.getRatings().stream().map(rating -> {

                ResponseEntity<Hotel> HotelResponseEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);

                Hotel hotel = HotelResponseEntity.getBody();

                rating.setHotel(hotel);

                return rating;

            }).collect(Collectors.toList());
        }
        return users;
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new NoUserFoundException());

        ResponseEntity<Rating[]> ratingListOfUser = restTemplate.getForEntity("http://RATINGSERVICE/ratings/users/"+userId, Rating[].class);
        log.info("{}",ratingListOfUser.getBody());
        user.setRatings(List.of(ratingListOfUser.getBody()));

        List<Rating> ratingList = user.getRatings().stream().map(rating -> {

//            ResponseEntity<Hotel> HotelResponseEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);

//            Hotel hotel = HotelResponseEntity.getBody();

//            we will be using feign client to make the http call insted of rest template

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);

            return rating;

        }).collect(Collectors.toList());
        return user;
    }

    @Override
    public User updateUser(String userId) {
        return null;
    }

    @Override
    public User deleteUser(String userId) {
        return null;
    }

    @Override
    public Rating publishRating(Rating rating) {

        return ratingService.saveRating(rating);
    }
}
