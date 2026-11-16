package com.example.employeemanagement.service;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {
    private final EmployeeCodeGenerator codeGenerator;

    public UtilityService(EmployeeCodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }

    public String formatName(String name) {
        return name == null ? null : name.trim();
    }

    public String generateEmployeeCode(Long id) {
        return codeGenerator.generate(id);
    }
}
