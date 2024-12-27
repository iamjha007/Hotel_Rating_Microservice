package com.sl.user.service.userService.services;

import com.sl.user.service.userService.entity.Rating;
import com.sl.user.service.userService.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();
    User getUser(String userId);

    User updateUser(String userId);

    User deleteUser(String userId);

    Rating publishRating(Rating rating);

}
