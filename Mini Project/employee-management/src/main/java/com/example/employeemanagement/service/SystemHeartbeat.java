package com.example.employeemanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SystemHeartbeat {
    private static final Logger log = LoggerFactory.getLogger(SystemHeartbeat.class);

    @Scheduled(fixedRate = 30000, initialDelay = 30000)
    public void logHeartbeat() {
        log.info("System running");
    }
}
