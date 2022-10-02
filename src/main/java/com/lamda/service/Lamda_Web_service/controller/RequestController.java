package com.lamda.service.Lamda_Web_service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.lamda.service.Lamda_Web_service.model.BloodPool;

import com.lamda.service.Lamda_Web_service.repository.BloodPoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.lamda.service.Lamda_Web_service.exception.ResourceNotFoundException;
import com.lamda.service.Lamda_Web_service.model.Requests;
import com.lamda.service.Lamda_Web_service.repository.RequestRepository;
import com.lamda.service.Lamda_Web_service.repository.UserRepository;
import com.lamda.service.Lamda_Web_service.repository.BloodPoolRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class RequestController {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BloodPoolRepository bloodPoolRepository;

    Map<String,String> response=new HashMap<String,String>();
    @PostMapping("/users/{userId}/requests")
    public ResponseEntity<?> createRequest(@PathVariable(value = "userId") Long userId,
                                           @RequestBody Requests new_request) {

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found user with id = " + userId);
        }

        Requests request=userRepository.findById(userId).map(user -> {
            new_request.setUser(user);
            return requestRepository.save(new_request);

        }).orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + userId));

        // Check any bank has no of units of blood >= amount requested
        Long amountRequested=new_request.getUnits();
        String bloodGroup=new_request.getBloodGroup();
        if(amountRequested<=0){
            response.put("message","Amount requested not positive");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        List<BloodPool>banks  = new ArrayList<BloodPool>();
        bloodPoolRepository.findByBloodGroup(bloodGroup).forEach(banks::add);
        if (banks.isEmpty()) {
            response.put("message","No Blood Bank has the blood group");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        for(BloodPool bank:banks){
            if(bank.getUnits()>=amountRequested){

                bank.decrementUnits(amountRequested);
                bloodPoolRepository.save(bank);
                response.put("message","Request fulfilled");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        response.put("message","Request unfulfilled");
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}