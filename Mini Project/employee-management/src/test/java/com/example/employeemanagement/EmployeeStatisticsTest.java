package com.example.employeemanagement;

import com.example.employeemanagement.dto.EmployeeStatistics;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.service.EmployeeReportService;
import com.example.employeemanagement.service.EmployeeStatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeStatisticsTest {
    @Test
    void statisticsIncludeDepartmentsWithoutEmployees() {
        EmployeeRepository employees = mock(EmployeeRepository.class);
        DepartmentRepository departments = mock(DepartmentRepository.class);
        EmployeeReportService total = mock(EmployeeReportService.class);
        Department it = new Department();
        it.setId(1L); it.setName("IT");
        Department hr = new Department();
        hr.setId(2L); hr.setName("HR");

        EmployeeRepository.DepartmentCount count = mock(EmployeeRepository.DepartmentCount.class);
        when(count.getDepartmentId()).thenReturn(1L);
        when(count.getTotalEmployees()).thenReturn(3L);
        when(employees.countByDepartment()).thenReturn(List.of(count));
        when(departments.findAll(Sort.by("name"))).thenReturn(List.of(hr, it));
        when(total.totalEmployees()).thenReturn(3L);

        EmployeeStatistics result = new EmployeeStatisticsService(employees, departments, total).getStatistics();
        assertEquals(3L, result.totalEmployees());
        assertEquals(2, result.departments().size());
        assertEquals(0L, result.departments().get(0).totalEmployees());
        assertEquals(3L, result.departments().get(1).totalEmployees());
    }
}
