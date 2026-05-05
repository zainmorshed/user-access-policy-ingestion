package com.zain.user_access_policy_ingestion.dto;

import java.time.LocalDate;

public class EnvironmentAccess {
    private String environment;
    private String accessStatus;
    private LocalDate startDate;
    private LocalDate endDate;

    public EnvironmentAccess(String environment, String accessStatus, LocalDate startDate, LocalDate endDate) {
        this.environment = environment;
        this.accessStatus = accessStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public EnvironmentAccess(){}

    public String getEnvironment() {
        return environment;
    }
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
    public String getAccessStatus() {
        return accessStatus;
    }
    public void setAccessStatus(String accessStatus) {
        this.accessStatus = accessStatus;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    
}
