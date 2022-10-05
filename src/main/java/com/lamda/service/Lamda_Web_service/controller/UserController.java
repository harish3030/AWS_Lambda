package com.lamda.service.Lamda_Web_service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lamda.service.Lamda_Web_service.exception.ResourceNotFoundException;
import com.lamda.service.Lamda_Web_service.model.User;
import com.lamda.service.Lamda_Web_service.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class UserController{
    @Autowired
    UserRepository userRepository;
    Map<String,String> response=new HashMap<String,String>();
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String blood_group) {
        List<User> users = new ArrayList<User>();

        if (blood_group == null)
            userRepository.findAll().forEach(users::add);
        else
            userRepository.findByBloodGroup(blood_group).forEach(users::add);

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User _user = userRepository.save(new User(user.getName(), user.getAge(),user.getCity(),
                user.getState(),user.getBloodGroup() ));
        response.put("message","User Added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}