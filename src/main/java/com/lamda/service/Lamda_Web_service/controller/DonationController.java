package com.lamda.service.Lamda_Web_service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONObject;
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

    Map<String,String> response=new HashMap<String,String>();
    @GetMapping("/users/{userId}/donations")
    public ResponseEntity<?> getAllDonationsByUserId(@PathVariable(value = "userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found User with id = " + userId);
        }

        List<Donations> donations = donationRepository.findByUserId(userId);
        JSONObject multiple = new JSONObject();
        multiple.put("donations",donations);
        return new ResponseEntity<>(multiple, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/donations")
    public ResponseEntity<?> createDonation(@PathVariable(value = "userId") Long userId,

                                                 @RequestBody Donations new_donation) {

        Long blood_bank_Id= new_donation.getBloodBankId();

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

        Long amountDonated=new_donation.getUnits();
        String bloodGroup=new_donation.getBloodGroup();
        if(amountDonated<=0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // No blood available of a particular group in the blood bank
        List<BloodPool>pools  = new ArrayList<BloodPool>();
        bloodPoolRepository.findByBankId(blood_bank_Id).forEach(pools::add);
        boolean exists = pools.stream().anyMatch(pool -> pool.getBloodGroup().equalsIgnoreCase(bloodGroup));
        if (pools.isEmpty() || !exists) {

            BloodPool new_blood_pool=new BloodPool( bloodGroup,amountDonated);
            BloodPool _bloodpool=bloodBankRepository.findById(blood_bank_Id).map(bank ->{
                new_blood_pool.setBank(bank);
                return bloodPoolRepository.save(new_blood_pool);

            }).orElseThrow(() -> new ResourceNotFoundException("Not found bank with id = " + blood_bank_Id));

        }
        // Blood of a particular group already present in pool.Now,add the new donation to it.
        else {
            for(BloodPool pool:pools){
                if(pool.getBloodGroup().equalsIgnoreCase(bloodGroup)){
                    pool.incrementUnits(amountDonated);
                    bloodPoolRepository.save(pool);
                    break;
                }
            }
        }
        response.put("message","Donation successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}