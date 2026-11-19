package com.example.employeemanagement.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Duration;

@Configuration
public class CacheConfig {
    @Bean
    public CaffeineCacheManager cacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager("employeeTotal");
        manager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(Duration.ofMinutes(1)).maximumSize(100));
        return manager;
    }
}
