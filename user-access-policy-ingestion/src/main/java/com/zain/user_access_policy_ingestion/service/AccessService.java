package com.zain.user_access_policy_ingestion.service;

import java.io.InputStream;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.zain.user_access_policy_ingestion.dto.AccessPolicy;
import com.zain.user_access_policy_ingestion.dto.User;
import com.zain.user_access_policy_ingestion.dto.UserAccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;
import java.util.ArrayList;

@Service
public class AccessService {

    public List<UserAccess> filterUsers() {
        AccessPolicy accessPolicy = accessPolicyParse();

        UserAccess userAccess = new UserAccess(null, null, null, null);


        List<UserAccess> userAccesses = new ArrayList<>();

        for (User user : accessPolicy.getUsers()) {

            LocalDate currentDate = LocalDate.now();

            if (user.isActive()){
                if (user.getEmail() != null && user.getEmail().contains("@")) {
                    if (!user.getAccessRules().isEmpty()) {

                       boolean validAccess = user.getAccessRules().stream()
                       .anyMatch(rule -> rule.getEnd_date() == null || rule.getEnd_date().isAfter(currentDate)); 


                       if (validAccess) {
                            userAccess.setUserId(user.getUser_id());
                            userAccess.setEmail(user.getEmail());
                            userAccess.setRoles(user.getRoles());
                            userAccess.setEnvironments(user.getAccessRules());


                        userAccesses.add(userAccess);
                       }
                    }
                }
            }
        }
        return userAccesses;
    }
        


    public AccessPolicy accessPolicyParse() {

        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.registerModule(new JavaTimeModule());
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("access-policy.yaml");
            return mapper.readValue(inputStream, AccessPolicy.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse yaml", e);
        }
    }


}
