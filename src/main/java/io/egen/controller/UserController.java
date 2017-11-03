package io.egen.controller;


import io.egen.entity.User;
import io.egen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllusers() {
        return repository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") String userId) {
        return repository.findById(userId);
    }

    @RequestMapping(method = RequestMethod.POST,
                    consumes = {MediaType.APPLICATION_JSON_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public User addUser(@RequestBody User user) {
        return repository.create(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT,
                    consumes = {MediaType.APPLICATION_JSON_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public User updateUser(@PathVariable("id") String userId, @RequestBody User user) {
        return repository.update(userId, user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE,
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public User deleteUser(@PathVariable("id") String userId) {
        return repository.remove(userId);
    }
}