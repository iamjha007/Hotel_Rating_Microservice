package com.sl.user.service.userService.Repositories;

import com.sl.user.service.userService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    //if you want to implement any custom method or query

}