package com.lamda.service.Lamda_Web_service.controller;



import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.lamda.service.Lamda_Web_service.exception.ResourceNotFoundException;
import com.lamda.service.Lamda_Web_service.model.Donations;
import com.lamda.service.Lamda_Web_service.model.BloodPool;
import com.lamda.service.Lamda_Web_service.repository.DonationRepository;

import com.lamda.service.Lamda_Web_service.repository.BloodBankRepository;
import com.lamda.service.Lamda_Web_service.repository.BloodPoolRepository;
import com.lamda.service.Lamda_Web_service.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DonationController {
    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BloodBankRepository bloodBankRepository;

    @Autowired
    private BloodPoolRepository bloodPoolRepository;


    @GetMapping("/users/{userId}/donations")
    public ResponseEntity<List<Donations>> getAllDonationsByUserId(@PathVariable(value = "userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found User with id = " + userId);
        }

        List<Donations> donations = donationRepository.findByUserID(userId);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }
    @GetMapping("/users/{blood_bank_Id}/donations")
    public ResponseEntity<List<Donations>> getAllDonationsByBloodBankId(@PathVariable(value = "blood_bank_Id") Long blood_bank_Id) {
        if (!bloodBankRepository.existsById(blood_bank_Id)) {
            throw new ResourceNotFoundException("Not found blood bank with id = " + blood_bank_Id);
        }

        List<Donations> donations = donationRepository.findByBloodBankID(blood_bank_Id);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }
    @PostMapping("/users/{userId}/{blood_bank_Id}/donations")
    public ResponseEntity<Donations> createDonation(@PathVariable(value = "userId") Long userId,
                                                   @PathVariable(value="blood_bank_Id")Long blood_bank_Id,
                                                 @RequestBody Donations new_donation) {

        if (!bloodBankRepository.existsById(blood_bank_Id)) {
            throw new ResourceNotFoundException("Not found blood bank with id = " + blood_bank_Id);
        }
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found user with id = " + userId);
        }

        Donations donation=userRepository.findById(userId).map(user -> {
            new_donation.setUser(user);
            return donationRepository.save(new_donation);

        }).orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + userId));

        // Add blood collected to the corresponding blood bank pool
        BloodPool new_blood_pool=new BloodPool(new_donation.getBloodGroup(), new_donation.getUnits());
        BloodPool _bloodpool=bloodBankRepository.findById(blood_bank_Id).map(bank ->{
            new_blood_pool.setBank(bank);
            return bloodPoolRepository.save(new_blood_pool);

        }).orElseThrow(() -> new ResourceNotFoundException("Not found bank with id = " + blood_bank_Id));;



        return new ResponseEntity<>(donation, HttpStatus.CREATED);
    }


}
