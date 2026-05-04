package com.zain.user_access_policy_ingestion.dto;

public class EnvironmentAccess {
    private String environment;
    private String accessStatus;
    private String startDate;
    private String endDate;

    public EnvironmentAccess(String environment, String accessStatus, String startDate, String endDate) {
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
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String ednDate) {
        this.endDate = ednDate;
    }


    
}
