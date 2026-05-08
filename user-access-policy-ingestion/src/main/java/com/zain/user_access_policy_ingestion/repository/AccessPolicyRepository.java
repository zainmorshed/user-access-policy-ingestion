package com.zain.user_access_policy_ingestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zain.user_access_policy_ingestion.entity.AccessPolicy;

public interface AccessPolicyRepository extends JpaRepository<AccessPolicy, Long>{

}
