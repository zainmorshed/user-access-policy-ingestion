package com.zain.user_access_policy_ingestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zain.user_access_policy_ingestion.entity.AccessPolicy;

@Repository
public interface AccessPolicyRepository extends JpaRepository<AccessPolicy, Long>{

}
