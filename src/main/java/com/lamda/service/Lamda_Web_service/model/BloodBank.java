package com.lamda.service.Lamda_Web_service.model;
import javax.persistence.*;

@Entity
@Table(name = "BloodBank")
public class BloodBank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_generator")
    private long blood_bank_id;

    @Column(name="blood_bank_name")
    private String blood_bank_name;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

}
