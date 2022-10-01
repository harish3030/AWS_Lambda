package com.lamda.service.Lamda_Web_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lamda.service.Lamda_Web_service.model.BloodPool;

import java.util.List;
public interface BloodPoolRepository extends JpaRepository<BloodPool, Long> {

      List<BloodPool>findByBloodGroup(String group);
}
