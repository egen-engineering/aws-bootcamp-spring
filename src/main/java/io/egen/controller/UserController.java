package io.egen.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import io.egen.entity.User;
import io.egen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userCache;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllusers() {
        List<User> users = userCache.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") String userId) {
        try {
            User user = userCache.findById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new User(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST,
                    consumes = {MediaType.APPLICATION_JSON_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<JsonNode> addUser(@RequestBody User user) {

        if (userCache.update(user)) {
            return new ResponseEntity<>(JsonNodeFactory.instance.objectNode(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(JsonNodeFactory.instance.objectNode(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE,
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JsonNode> deleteUser(@PathVariable("id") String userId) {

        if (userCache.remove(userId)) {
            return new ResponseEntity<>(JsonNodeFactory.instance.objectNode(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(JsonNodeFactory.instance.objectNode(), HttpStatus.BAD_REQUEST);
        }
    }
}