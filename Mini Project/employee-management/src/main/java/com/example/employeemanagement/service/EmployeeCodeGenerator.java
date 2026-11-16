package com.example.employeemanagement.service;

public class EmployeeCodeGenerator {
    private final String prefix;

    public EmployeeCodeGenerator(String prefix) {
        this.prefix = prefix;
    }

    public String generate(Long number) {
        return prefix + String.format("%04d", number);
    }
}
