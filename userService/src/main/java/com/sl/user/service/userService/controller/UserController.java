package com.sl.user.service.userService.controller;

import com.sl.user.service.userService.entity.Rating;
import com.sl.user.service.userService.entity.User;
import com.sl.user.service.userService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        User user1=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){

        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){

        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/publishrating")
    public ResponseEntity<Rating> publishRating(@RequestBody Rating rating){

        return ResponseEntity.ok(userService.publishRating(rating));
    }


}
