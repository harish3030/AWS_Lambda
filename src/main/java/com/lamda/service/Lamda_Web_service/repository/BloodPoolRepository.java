package com.lamda.service.Lamda_Web_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lamda.service.Lamda_Web_service.model.BloodPool;

import java.util.List;

public interface BloodPoolRepository extends JpaRepository<BloodPool, Long> {
      List<BloodPool>findByBloodGroup(String group); // used to find blood banks that have a given blood group
      List<BloodPool>findByBankId(Long blood_bank_id); // used to find donations made to a blood bank
}
