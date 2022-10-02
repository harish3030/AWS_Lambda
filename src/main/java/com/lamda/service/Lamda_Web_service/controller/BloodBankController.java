package com.lamda.service.Lamda_Web_service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.lamda.service.Lamda_Web_service.model.Bank;


import com.lamda.service.Lamda_Web_service.repository.BloodBankRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class BloodBankController {
    @Autowired
    private BloodBankRepository bloodBankRepository;

    Map<String,String> response=new HashMap<String,String>();
    @GetMapping("/banks")
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> bank = new ArrayList<Bank>();


        bloodBankRepository.findAll().forEach(bank::add);


        if (bank.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    @PostMapping("/banks")
    public ResponseEntity<?> createBloodBank(@RequestBody Bank bank) {
        Bank _bank= bloodBankRepository.save(new Bank(bank.getName(),bank.getCity(),bank.getState()));
        response.put("message","Blood Bank created");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
