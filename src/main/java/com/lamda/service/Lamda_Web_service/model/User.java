package com.lamda.service.Lamda_Web_service.model;

import javax.persistence.*;

@Entity
@Table(name = "UserTable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "age")
    private int age;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name="blood_group")
    private String blood_group;

    public User() {

    }

    public User(String name,int age, String city, String state,String blood_group) {
        this.user_name=name;
        this.age = age;
        this.city = city;
        this.state=state;
        this.blood_group=blood_group;
    }
    public long getId() {
        return user_id;
    }

    public String getName() {
        return user_name;
    }
    public int getAge() {
        return age;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public String getBloodType(){
        return blood_group;
    }
    public void setName(String name){
        this.user_name=name;
    }
    public void setBloodType(String type){
        this.blood_group=type;
    }




}

