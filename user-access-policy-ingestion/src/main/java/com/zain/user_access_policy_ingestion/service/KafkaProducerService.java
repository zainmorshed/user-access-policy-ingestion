package com.zain.user_access_policy_ingestion.service;

import org.springframework.stereotype.Service;

import org.apache.kafka.clients.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.zain.user_access_policy_ingestion.entity.*;


@Service
public class KafkaProducerService {
    
    @Autowired
    private KafkaTemplate<String, AccessPolicy> kafkaTemplate;

    public void sendPolicy(AccessPolicy policy) {
        kafkaTemplate.send("access-policy-topic", policy);
    }

}
