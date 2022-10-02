package com.lamda.service.Lamda_Web_service.model;
import javax.persistence.*;

@Entity
@Table(name = "BloodBank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_generator")
    private Long id;

    @Column(name="blood_bank_name")
    private String blood_bank_name;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    public Bank(String name, String city, String state) {
        this.blood_bank_name=name;
        this.city = city;
        this.state=state;

    }
    public String getName(){
        return blood_bank_name;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
}