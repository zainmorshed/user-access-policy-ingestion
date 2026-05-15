package com.zain.user_access_policy_ingestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class UserAccessPolicyIngestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAccessPolicyIngestionApplication.class, args);
	}

}
