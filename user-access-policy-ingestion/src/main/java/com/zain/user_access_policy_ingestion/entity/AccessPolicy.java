package com.zain.user_access_policy_ingestion.entity;

import java.util.*;

import jakarta.persistence.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;



@Entity
public class AccessPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metadata_id")
    private PolicyMetadata policyMetadata;

    @OneToMany(mappedBy = "accessPolicy", cascade = CascadeType.ALL)
    private List<User> users;

    
    public AccessPolicy(PolicyMetadata policyMetadata, List<User> users) {
        this.policyMetadata = policyMetadata;
        this.users = users;
    }

    public AccessPolicy(){}



    public PolicyMetadata getPolicyMetadata() {
        return policyMetadata;
    }


    public void setPolicyMetadata(PolicyMetadata policyMetadata) {
        this.policyMetadata = policyMetadata;
    }


    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }

}
