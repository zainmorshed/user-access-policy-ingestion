package com.zain.user_access_policy_ingestion.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zain.user_access_policy_ingestion.emum.*;


public class User {
    
    private String user_id;
    private String email;
    private boolean active;
    private List<Role> roles;

    @JsonProperty("access_rules")
    private List<AccessRules> accessRules;

    public User(String user_id, String email, boolean active, List<Role> roles, List<AccessRules> accessRules) {
        this.user_id = user_id;
        this.email = email;
        this.active = active;
        this.roles = roles;
        this.accessRules = accessRules;
    }

    public User(){}

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public List<AccessRules> getAccessRules() {
        return accessRules;
    }
    public void setAccessRules(List<AccessRules> accessRules) {
        this.accessRules = accessRules;
    }


}
