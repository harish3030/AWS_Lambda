package com.lamda.service.Lamda_Web_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lamda.service.Lamda_Web_service.model.User;

import java.util.List;
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByBloodType(String blood_group);

}