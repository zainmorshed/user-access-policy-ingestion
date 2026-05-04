package com.zain.user_access_policy_ingestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
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

    @GetMapping("/access-rules")
    public List<UserAccess> getAllusersAccess(){

        return accessService.filterUsers();

    }
}
