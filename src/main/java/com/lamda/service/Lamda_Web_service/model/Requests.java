package com.lamda.service.Lamda_Web_service.model;
import javax.persistence.*;
@Entity
@Table(name = "Requests")
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_generator")
    private Long request_id;
    @Column(name="blood_group")
    private String bloodGroup;
    @Column(name="units")
    private Long units;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Id", nullable = false)
    private User user;
    public Requests(){

    }
    public Long getUnits(){
        return units;
    }
    public String getBloodGroup(){
        return bloodGroup;
    }
    public void setUser(User user) {
        this.user = user;
    }

   }
