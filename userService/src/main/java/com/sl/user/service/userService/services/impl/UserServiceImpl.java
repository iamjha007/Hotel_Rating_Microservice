package com.sl.user.service.userService.services.impl;

import com.sl.user.service.userService.Repositories.UserRepository;
import com.sl.user.service.userService.entity.User;
import com.sl.user.service.userService.exceptions.NoUserFoundException;
import com.sl.user.service.userService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new NoUserFoundException());
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
