package com.mohammed.rest.webservices.services.impl;

import com.mohammed.rest.webservices.Exception.NoUserExistException;
import com.mohammed.rest.webservices.Exception.UserAlreadyExistException;
import com.mohammed.rest.webservices.Exception.UserNotFoundException;
import com.mohammed.rest.webservices.entites.User;
import com.mohammed.rest.webservices.repositories.UserRepository;
import com.mohammed.rest.webservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * @return
     */
    @Override
    public List<User> findAllUsers() {
        List<User> listUsers = userRepository.findAll();
        if(listUsers.isEmpty()) throw new NoUserExistException("there is no users in the data base");
        return listUsers;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User findOneUser(Long id) {
       Optional<User> userOptional = userRepository.findById(id);
       if(!userOptional.isPresent()) throw new UserNotFoundException("user with the id :" + id + " not found");
           return  userOptional.get();
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User saveUser(User user) {
        Optional<User> userOptional = userRepository.findByName(user.getName());
        if(!userOptional.isPresent()) {
            return userRepository.save(user);
        }
        else {
            throw new UserAlreadyExistException("user already exist");
        }
        //return userRepository.save(new User(user.getName()));
    }
}
