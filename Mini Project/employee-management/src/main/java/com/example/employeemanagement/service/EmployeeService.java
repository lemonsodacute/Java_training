package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeForm;
import com.example.employeemanagement.exception.BadRequestException;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class EmployeeService {
    private final EmployeeRepository employees;
    private final DepartmentRepository departments;
    private final UtilityService utils;

    public EmployeeService(EmployeeRepository employees, DepartmentRepository departments, UtilityService utils) {
        this.employees = employees; this.departments = departments; this.utils = utils;
    }
    @Transactional(readOnly = true)
    public List<Employee> search(String name, Long departmentId) {
        boolean hasName = name != null && !name.isBlank();
        if (hasName && departmentId != null) return employees.findByNameContainingIgnoreCaseAndDepartment_Id(name, departmentId);
        if (hasName) return employees.findByNameContainingIgnoreCase(name);
        if (departmentId != null) return employees.findByDepartment_Id(departmentId);
        return employees.findAll();
    }
    @Transactional(readOnly = true)
    public Employee get(Long id) {
        return employees.findById(id).orElseThrow(() -> new ResourceNotFoundException("Khong tim thay nhan vien ID " + id));
    }
    @Transactional
    public Employee create(EmployeeForm form) {
        if (employees.existsByEmailIgnoreCase(form.getEmail().trim()))
            throw new BadRequestException("Email da ton tai");
        Employee employee = new Employee();
        fill(employee, form);
        return employees.save(employee);
    }
    @Transactional
    public Employee update(Long id, EmployeeForm form) {
        Employee employee = get(id);
        if (employees.existsByEmailIgnoreCaseAndIdNot(form.getEmail().trim(), id))
            throw new BadRequestException("Email da ton tai");
        fill(employee, form);
        return employees.save(employee);
    }
    @Transactional
    public void delete(Long id) {
        Employee employee = get(id);
        employees.delete(employee);
    }
    private void fill(Employee employee, EmployeeForm form) {
        Department dep = departments.findById(form.getDepartmentId())
            .orElseThrow(() -> new BadRequestException("Phong ban khong ton tai"));
        employee.setName(utils.formatName(form.getName()));
        employee.setEmail(form.getEmail().trim());
        employee.setDepartment(dep);
    }
}
