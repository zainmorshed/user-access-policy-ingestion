package com.zain.user_access_policy_ingestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zain.user_access_policy_ingestion.service.AccessService;
import com.zain.user_access_policy_ingestion.dto.*;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class AccessController {


    @Autowired
    AccessService accessService = new AccessService();
    
    // private final AccessService accessService;

    // public AccessController(AccessService accessService){
    //     this.accessService = accessService;
    // }
    // public String postMethodName(@RequestBody String entity) {
    //     return entity;
    // }

    @GetMapping("/access-rules/yaml")
    public List<UserAccess> getUserAccessYaml(){
        AccessPolicy policy = accessService.accessPolicyParseYaml();
        return accessService.filterUsers(policy);
        // return accessService.filterUsers();

    }

    @PostMapping("/access-rules/json")
    public List<UserAccess> getUserAccessJson(@RequestBody String request){
        AccessPolicy policy = accessService.accessPolicyParseJson(request);
        return accessService.filterUsers(policy);
    }

    // @GetMapping("/access-rule/{id}")
    // public UserAccess getUserAccess(@PathVariable)
}
