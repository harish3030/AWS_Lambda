package com.lamda.service.Lamda_Web_service.model;

import javax.persistence.*;

@Entity
@Table(name = "Blood")
public class BloodPool {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blood_generator")
    private long blood_count;

    @Column(name="blood_group")
    private String blood_group;

    @Column(name="units")
    private long units;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blood_bank_id", nullable = false)
    private BloodBank bank;
    public BloodPool(String group,long units){
        this.blood_group=group;
        this.units=units;
    }
    public void setBank(BloodBank bank) {
        this.bank = bank;
    }
    public String getBloodGroup(){
        return blood_group;
    }
    public long getUnits(){
        return units;
    }

    public void setUnits(long val){
        if(units>=val) units-=val;
    }

}
