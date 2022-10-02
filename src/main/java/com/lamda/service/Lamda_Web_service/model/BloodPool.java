package com.lamda.service.Lamda_Web_service.model;

import javax.persistence.*;

@Entity
@Table(name = "Blood")
public class BloodPool {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blood_generator")
    private Long blood_count;

    @Column(name="bloodGroup")
    private String bloodGroup;

    @Column(name="units")
    private Long units;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bloodBankId", nullable = false)
    private Bank bank;
    public BloodPool(){

    }
    public BloodPool(String group,Long units){
        this.bloodGroup=group;
        this.units=units;
    }
    public void setBank(Bank bank) {
        this.bank = bank;
    }
    public String getBloodGroup(){
        return bloodGroup;
    }
    public Long getUnits(){
        return units;
    }

    public void decrementUnits(Long val){
        if(units>=val) units-=val;
    }
    public void incrementUnits(Long val){
        units+=val;
    }

}
