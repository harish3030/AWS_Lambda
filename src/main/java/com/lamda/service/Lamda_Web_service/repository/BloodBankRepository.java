package com.lamda.service.Lamda_Web_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lamda.service.Lamda_Web_service.model.Bank;

import java.util.List;
public interface BloodBankRepository extends JpaRepository<Bank, Long> {


}