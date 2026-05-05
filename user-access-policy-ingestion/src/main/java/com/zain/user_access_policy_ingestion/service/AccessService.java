package com.zain.user_access_policy_ingestion.service;

import java.io.InputStream;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.zain.user_access_policy_ingestion.dto.AccessPolicy;
import com.zain.user_access_policy_ingestion.dto.AccessRules;
import com.zain.user_access_policy_ingestion.dto.EnvironmentAccess;
import com.zain.user_access_policy_ingestion.dto.User;
import com.zain.user_access_policy_ingestion.dto.UserAccess;

import tools.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


import java.util.List;
import java.util.ArrayList;

@Service
public class AccessService {

    public List<UserAccess> filterUsers(AccessPolicy policy) {
        // AccessPolicy accessPolicy = accessPolicyParseYaml();


        List<UserAccess> userAccesses = new ArrayList<>();

        for (User user : policy.getUsers()) {
            UserAccess userAccess = new UserAccess(null, null, null, null);

            LocalDate currentDate = LocalDate.now();

            if (user.isActive()){
                // userAccess.setAccessStatus("ACTIVE");
                if (user.getEmail() != null && user.getEmail().contains("@")) {
                    if (!user.getAccessRules().isEmpty()) {

                       boolean validAccess = user.getAccessRules().stream()
                       .anyMatch(rule -> rule.getEnd_date() == null || rule.getEnd_date().isAfter(currentDate)); 

                        List<EnvironmentAccess> environments = new ArrayList<>();
                       for (AccessRules rule : user.getAccessRules()) {

                            String status = determineAccessStatus(rule, currentDate);

                            EnvironmentAccess env = new EnvironmentAccess();
                            env.setEnvironment(rule.getEnvironment());
                            env.setStartDate(rule.getStart_date());
                            env.setEndDate(rule.getEnd_date());
                            env.setAccessStatus(status);

                            environments.add(env);
                       }


                       if (validAccess) {
                            userAccess.setUserId(user.getUser_id());
                            userAccess.setEmail(user.getEmail());
                            userAccess.setRoles(user.getRoles());
                            userAccess.setEnvironments(environments);
                        
                            
                            userAccesses.add(userAccess);
                       }
                    }
                }
            }
        }
        return userAccesses;
        
    }


    private String determineAccessStatus(AccessRules rule, LocalDate now) {
        if (rule.getEnd_date() == null) {
            return "OPEN_ENDED";
        }

        if (rule.getEnd_date().isBefore(now)){
            return "EXPIRED";

        }

        if(!rule.getStart_date().isAfter(now)) {
            return "ACTIVE";
        }

        return "NOT_STARTED";
    }

        


    public AccessPolicy accessPolicyParseYaml() {

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.registerModule(new JavaTimeModule());
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("access-policy.yaml");

        try {
            return mapper.readValue(inputStream, AccessPolicy.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse yaml", e);
        }
    }

    public AccessPolicy accessPolicyParseJson(String request) {


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);

        try {
            return mapper.readValue(request, AccessPolicy.class);
        } catch(Exception e) {
            throw new RuntimeException("Failed to parse json", e);
        }
    }


}
