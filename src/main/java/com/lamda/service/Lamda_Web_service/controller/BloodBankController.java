package com.lamda.service.Lamda_Web_service.controller;

import java.util.ArrayList;
import java.util.List;


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
    public ResponseEntity<Bank> createBloodBank(@RequestBody Bank bank) {
        Bank _bank= bloodBankRepository.save(new Bank(bank.getName(),bank.getCity(),bank.getState()));
        return new ResponseEntity<>(_bank, HttpStatus.CREATED);
    }
}
