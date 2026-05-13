package com.zain.user_access_policy_ingestion.service;

import java.io.InputStream;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.zain.user_access_policy_ingestion.entity.AccessPolicy;
import com.zain.user_access_policy_ingestion.entity.AccessRules;
import com.zain.user_access_policy_ingestion.entity.User;
import com.zain.user_access_policy_ingestion.entity.PolicyMetadata;

import com.zain.user_access_policy_ingestion.dto.EnvironmentAccess;
import com.zain.user_access_policy_ingestion.dto.UserAccess;
import com.zain.user_access_policy_ingestion.repository.AccessPolicyRepository;

import tools.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Service
public class AccessService {



    // @Autowired
    private final AccessPolicyRepository accessPolicyRepository;
    
    public AccessService(AccessPolicyRepository accessPolicyRepository){
        this.accessPolicyRepository = accessPolicyRepository;
    }


    // //Map DTO to Entity - manually transfering the data
    // public com.zain.user_access_policy_ingestion.entity.AccessPolicy savePolicy(AccessPolicy dto) {
    // // 1. Create a new Entity instance
    // com.zain.user_access_policy_ingestion.entity.AccessPolicy entity = new com.zain.user_access_policy_ingestion.entity.AccessPolicy();
    
    // // 2. Copy fields from DTO to Entity
    // // entity.setName(dto.getName()); // Example
    
    // // 3. Save the entity
    // return accessPolicyRepository.save(entity);
    // }

    private void linkRelationships(AccessPolicy policy) {



        if (policy.getPolicyMetadata() != null) {
            // link metadata back to policy
            policy.getPolicyMetadata().setAccessPolicy(policy);
        }else {
            throw new IllegalArgumentException("policyMetadata is required");
        }
        
        

        for (User user : policy.getUsers()) {

            // link user to policy
            user.setAccessPolicy(policy);

            for (AccessRules rule : user.getAccessRules()) {

                // link access rule to user
                rule.setUser(user);
            }
        }
    }

    public AccessPolicy savePolicy(AccessPolicy policy) {
        linkRelationships(policy);
        return accessPolicyRepository.save(policy);
    }



    public void deletePolicy(Long id) {
        accessPolicyRepository.deleteById(id);
    }

    public List<UserAccess> filterUsers(AccessPolicy policy) {
        // AccessPolicy accessPolicy = accessPolicyParseYaml();


        List<UserAccess> userAccesses = new ArrayList<>();
        //currently using dto AccessPolicy - not entity
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
