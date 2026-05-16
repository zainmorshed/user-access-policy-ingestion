package com.zain.user_access_policy_ingestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zain.user_access_policy_ingestion.entity.PolicyJob;


@Repository
public interface PolicyJobRepository extends JpaRepository <PolicyJob, Long>{
    
}
