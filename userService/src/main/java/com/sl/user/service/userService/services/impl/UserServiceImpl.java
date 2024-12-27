package com.sl.user.service.userService.services.impl;

import com.sl.user.service.userService.Repositories.UserRepository;
import com.sl.user.service.userService.entity.Rating;
import com.sl.user.service.userService.entity.User;
import com.sl.user.service.userService.exceptions.NoUserFoundException;
import com.sl.user.service.userService.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public User saveUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        List<User> users = userRepository.findAll();

        for(User user : users){
            ResponseEntity<Rating[]> ratingList = restTemplate.getForEntity("http://localhost:8083/ratings/users/"+user.getId(), Rating[].class);
            log.info("{}",ratingList.getBody());
            user.setRatings(List.of(ratingList.getBody()));
        }
        return users;
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new NoUserFoundException());

        ResponseEntity<Rating[]> ratingList = restTemplate.getForEntity("http://localhost:8083/ratings/users/"+userId, Rating[].class);
        log.info("{}",ratingList.getBody());
        user.setRatings(List.of(ratingList.getBody()));
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
}
