package com.lamda.service.Lamda_Web_service.model;
import javax.persistence.*;

@Entity
@Table(name = "Requests")
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_generator")
    private long request_id;

    @Column(name="blood_group")
    private String blood_group;

    @Column(name="units")
    private long units;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public long getUnits(){
        return units;
    }
    public String getBloodGroup(){
        return blood_group;
    }
    public void setUser(User user) {
        this.user = user;
    }



}
