package com.lamda.service.Lamda_Web_service.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.lamda.service.Lamda_Web_service.exception.ResourceNotFoundException;

import com.lamda.service.Lamda_Web_service.repository.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.lamda.service.Lamda_Web_service.model.BloodPool;

import com.lamda.service.Lamda_Web_service.repository.BloodBankRepository;
import com.lamda.service.Lamda_Web_service.repository.BloodPoolRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class BloodPoolController {
    @Autowired
    private BloodPoolRepository bloodPoolRepository;
    @Autowired
    private BloodBankRepository bloodBankRepository;
    @GetMapping("/banks/{blood_bank_Id}/donations")
    public ResponseEntity<?> getUnitsByBloodBankId(@PathVariable(value = "blood_bank_Id") Long blood_bank_Id) {
        if (!bloodBankRepository.existsById(blood_bank_Id)) {
            throw new ResourceNotFoundException("Not found blood bank with id = " + blood_bank_Id);
        }

        List<BloodPool> donations = bloodPoolRepository.findByBankId(blood_bank_Id);
        Map<String,Long>donations1=new HashMap<>();
        for(BloodPool d:donations){
            donations1.put(d.getBloodGroup(),d.getUnits());
        }
        return new ResponseEntity<>(donations1, HttpStatus.OK);
    }

}
