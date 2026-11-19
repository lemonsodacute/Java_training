package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeStatistics;
import com.example.employeemanagement.dto.EmployeeStatistics.DepartmentStatistics;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeStatisticsService {
    private final EmployeeRepository employees;
    private final DepartmentRepository departments;
    private final EmployeeReportService reports;

    public EmployeeStatisticsService(EmployeeRepository employees, DepartmentRepository departments,
                                     EmployeeReportService reports) {
        this.employees = employees;
        this.departments = departments;
        this.reports = reports;
    }

    @Transactional(readOnly = true)
    public EmployeeStatistics getStatistics() {
        Map<Long, Long> counts = new HashMap<>();
        for (EmployeeRepository.DepartmentCount row : employees.countByDepartment()) {
            counts.put(row.getDepartmentId(), row.getTotalEmployees());
        }
        List<DepartmentStatistics> result = departments.findAll(Sort.by("name")).stream()
            .map(dep -> new DepartmentStatistics(dep.getId(), dep.getName(), counts.getOrDefault(dep.getId(), 0L)))
            .toList();
        return new EmployeeStatistics(reports.totalEmployees(), result);
    }
}
