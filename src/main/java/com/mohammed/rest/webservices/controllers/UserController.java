package com.mohammed.rest.webservices.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.mohammed.rest.webservices.entites.User;
import com.mohammed.rest.webservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/users")
    public ResponseEntity<List<EntityModel<User>>> getUsers() {

        List<User> userList = userService.findAllUsers();

        List<EntityModel<User>> entityModelList;
        entityModelList = userList.stream().map(user -> {
            EntityModel<User> model = EntityModel.of(user);
            WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getOneUser(user.getId()));
            model.add(linkBuilder.withRel("user " + user.getId()));
            return model;
        }).toList();

        return new ResponseEntity<>(entityModelList, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> getOneUser(
            @PathVariable Long id) {
        User user = userService.findOneUser(id);
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getUsers());

        model.add(linkBuilder.withRel("allUsers"));

        return new ResponseEntity<>(model, HttpStatus.FOUND);
    }

    @PostMapping("/users/add")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/helloworld_i18n", headers="Accept=application/json")
    public String helloworld(
            //@RequestHeader(name = "Accept-Language", defaultValue = "en", required = false) Locale locale
    ) {

        System.out.println(messageSource.getMessage(
                "language",
                null,
                "default message",
                LocaleContextHolder.getLocale()));


        String message = messageSource.getMessage(
                "language",
                null,
                "default message",
                LocaleContextHolder.getLocale());
        return message;
    }
}
