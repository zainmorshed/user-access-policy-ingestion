package com.zain.user_access_policy_ingestion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.zain.user_access_policy_ingestion.dto.AccessPolicy;
import com.zain.user_access_policy_ingestion.service.AccessService;

@Service
public class KafkaConsumerService {
    
    @Autowired
    private AccessService accessService;

    @KafkaListener(topics = "access-policy-topic", groupId = "policy-group")
    public void consume(AccessPolicy policy) {

        System.out.println("CONSUMED: " + policy);

        accessService.savePolicy(policy);

    }
}
