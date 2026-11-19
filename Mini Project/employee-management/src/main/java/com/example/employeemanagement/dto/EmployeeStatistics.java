package com.example.employeemanagement.dto;

import java.util.List;

public record EmployeeStatistics(long totalEmployees, List<DepartmentStatistics> departments) {
    public record DepartmentStatistics(Long departmentId, String departmentName, long totalEmployees) {}
}
