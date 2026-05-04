package com.zain.user_access_policy_ingestion.dto;

import java.util.List;
import com.zain.user_access_policy_ingestion.emum.*;

public class UserAccess {
    private String userId;
    private String email;
    private List<Role> roles;
    private List<AccessRules> environments;
    


    public UserAccess(String userId, String email, List<Role> roles, List<AccessRules> environments) {
        this.userId = userId;
        this.email = email;
        this.roles = roles;
        this.environments = environments;
      
    }

    public UserAccess(){}


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public List<AccessRules> getEnvironments() {
        return environments;
    }
    public void setEnvironments(List<AccessRules> environments) {
        this.environments = environments;
    }

    
}
