package com.mohammed.rest.webservices.services;

import com.mohammed.rest.webservices.entites.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findOneUser(Long id);
    User saveUser (User user);

}
