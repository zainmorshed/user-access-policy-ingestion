package com.zain.user_access_policy_ingestion.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class PolicyMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "policyMetadata")
    private AccessPolicy accessPolicy;

    private String policy_id;
    private String source_system;
    private LocalDate effective_date;

    public PolicyMetadata(String policy_id, String source_system, LocalDate effective_date) {
        this.policy_id = policy_id;
        this.source_system = source_system;
        this.effective_date = effective_date;
    }

    public PolicyMetadata(){}
    
    public String getPolicy_id() {
        return policy_id;
    }
    public void setPolicy_id(String policy_id) {
        this.policy_id = policy_id;
    }
    public String getSource_system() {
        return source_system;
    }
    public void setSource_system(String source_system) {
        this.source_system = source_system;
    }
    public LocalDate getEffective_date() {
        return effective_date;
    }
    public void setEffective_date(LocalDate effective_date) {
        this.effective_date = effective_date;
    }


}
