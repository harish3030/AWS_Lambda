package com.lamda.service.Lamda_Web_service.model;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Donation")
public class Donations {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donation_generator")
    private Long donation_id;

    @Column(name="blood_group")
    private String blood_group;

    @Column(name="units")
    private Long units;

    @Column(name="bloodBankId")
    private Long bloodBankId;
    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Id", nullable = false)
    @JsonIgnore
    private User user;

    public String getBloodGroup(){
        return blood_group;
    }
    public Long getUnits(){
        return units;
    }

    public Long getBankId(){ return bloodBankId;}

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }

}
