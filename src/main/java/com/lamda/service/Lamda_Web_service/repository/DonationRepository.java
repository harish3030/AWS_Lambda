package com.lamda.service.Lamda_Web_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lamda.service.Lamda_Web_service.model.Donations;

import java.util.List;
public interface DonationRepository extends JpaRepository<Donations, Long> {
    List<Donations>findByUserId(Long user_id);
}