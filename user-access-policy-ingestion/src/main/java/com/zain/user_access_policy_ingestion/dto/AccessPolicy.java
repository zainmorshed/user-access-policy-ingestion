package com.zain.user_access_policy_ingestion.dto;

import java.util.*;

public class AccessPolicy {
    private policy_metadata policy_metadata;
    private List<User> users;

    
    public AccessPolicy(policy_metadata policy_metadata, List<User> users) {
        this.policy_metadata = policy_metadata;
        this.users = users;
    }

    public AccessPolicy(){}


    public policy_metadata getpolicy_metadata() {
        return policy_metadata;
    }


    public void setpolicy_metadata(policy_metadata policy_metadata) {
        this.policy_metadata = policy_metadata;
    }


    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }

}
