package com.lamda.service.Lamda_Web_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lamda.service.Lamda_Web_service.model.Requests;

import java.util.List;
public interface RequestRepository extends JpaRepository<Requests, Long> {
    List<Requests> findByUserId(Long user_id);


}