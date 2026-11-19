package com.example.employeemanagement.controller;

import com.example.employeemanagement.service.EmployeeReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/reports/employees")
public class EmployeeReportController {
    private final EmployeeReportService reports;

    public EmployeeReportController(EmployeeReportService reports) {
        this.reports = reports;
    }

    @GetMapping("/total")
    public Map<String, Long> total() {
        return Map.of("totalEmployees", reports.totalEmployees());
    }
}
