package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeStatistics;
import com.example.employeemanagement.service.EmployeeStatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports/employees")
class EmployeeStatisticsApiController {
    private final EmployeeStatisticsService reports;

    EmployeeStatisticsApiController(EmployeeStatisticsService reports) {
        this.reports = reports;
    }

    @GetMapping("/statistics")
    public EmployeeStatistics statistics() {
        return reports.getStatistics();
    }
}

@Controller
class EmployeeStatisticsPageController {
    private final EmployeeStatisticsService reports;

    EmployeeStatisticsPageController(EmployeeStatisticsService reports) {
        this.reports = reports;
    }

    @GetMapping("/employees/statistics")
    public String statistics(Model model) {
        model.addAttribute("statistics", reports.getStatistics());
        return "employee-statistics";
    }
}
