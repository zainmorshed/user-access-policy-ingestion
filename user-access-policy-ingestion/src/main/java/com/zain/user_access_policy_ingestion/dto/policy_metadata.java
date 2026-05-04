package com.zain.user_access_policy_ingestion.dto;

import java.time.LocalDate;
import java.util.List;

public class policy_metadata {
    private String policy_id;
    private String source_system;
    private LocalDate effective_date;

    public policy_metadata(String policy_id, String source_system, LocalDate effective_date) {
        this.policy_id = policy_id;
        this.source_system = source_system;
        this.effective_date = effective_date;
    }

    public policy_metadata(){}
    
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
