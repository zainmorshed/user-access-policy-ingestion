package com.zain.user_access_policy_ingestion.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class AccessRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String environment;
    private LocalDate start_date;
    private LocalDate end_date;

    
    public AccessRules(String environment, LocalDate start_date, LocalDate end_date) {
        this.environment = environment;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public AccessRules(){}


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEnvironment() {
        return environment;
    }


    public void setEnvironment(String environment) {
        this.environment = environment;
    }


    public LocalDate getStart_date() {
        return start_date;
    }


    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }


    public LocalDate getEnd_date() {
        return end_date;
    }


    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
}
