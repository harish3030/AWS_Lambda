package com.lamda.service.Lamda_Web_service.model;
import javax.persistence.*;

@Entity
@Table(name = "Requests")
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_generator")
    private Long request_id;

    @Column(name="blood_group")
    private String blood_group;

    @Column(name="units")
    private Long units;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Id", nullable = false)
    private User user;
    public Long getUnits(){
        return units;
    }
    public String getBloodGroup(){
        return blood_group;
    }
    public void setUser(User user) {
        this.user = user;
    }



}
