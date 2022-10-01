package com.lamda.service.Lamda_Web_service.model;
import javax.persistence.*;

@Entity
@Table(name = "Donation")
public class Donations {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donation_generator")
    private long donation_id;

    @Column(name="blood_group")
    private String blood_group;

    @Column(name="units")
    private long units;

    @Column(name="blood_bank_id")
    private long bank_id;
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getBloodGroup(){
        return blood_group;
    }
    public long getUnits(){
        return units;
    }

    public void setUser(User user) {
        this.user = user;
    }






}
