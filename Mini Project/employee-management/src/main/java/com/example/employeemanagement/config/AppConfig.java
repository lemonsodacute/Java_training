package com.example.employeemanagement.config;

import com.example.employeemanagement.service.EmployeeCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public EmployeeCodeGenerator employeeCodeGenerator() {
        return new EmployeeCodeGenerator("EMP-");
    }
}
