package com.zain.user_access_policy_ingestion.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "policyExecutor")
    public Executor policyExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(2);
        executor.setThreadNamePrefix("policy-worker-");
        
        executor.initialize();

        return executor;
    }
    
}
