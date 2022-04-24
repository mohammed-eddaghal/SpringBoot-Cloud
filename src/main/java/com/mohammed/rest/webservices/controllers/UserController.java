package com.mohammed.rest.webservices.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.mohammed.rest.webservices.entites.User;
import com.mohammed.rest.webservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> getOneUser(@PathVariable Long id){
        User user = userService.findOneUser(id);
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getUsers());

        model.add(linkBuilder.withRel("allUsers"));

        return new ResponseEntity<>(model, HttpStatus.FOUND);
    }

    @PostMapping("/users/add")
    public ResponseEntity<User> addUser (@Valid @RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
}
