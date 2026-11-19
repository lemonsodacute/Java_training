package com.example.employeemanagement.service;

import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeReportService {
    private final EmployeeRepository employees;

    public EmployeeReportService(EmployeeRepository employees) {
        this.employees = employees;
    }

    @Cacheable("employeeTotal")
    @Transactional(readOnly = true)
    public long totalEmployees() {
        return employees.count();
    }
}
