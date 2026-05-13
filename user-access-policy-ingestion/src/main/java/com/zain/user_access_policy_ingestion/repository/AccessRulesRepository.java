package com.zain.user_access_policy_ingestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zain.user_access_policy_ingestion.entity.AccessRules;

//Use optionally if you want o tquery rules directly, filter rules (by environments, dates, etc.), manage them independently
@Repository
public interface AccessRulesRepository extends JpaRepository<AccessRules, Long>{

}
