package com.example.employeemanagement;

import com.example.employeemanagement.controller.EmployeePageController;
import com.example.employeemanagement.dto.EmployeeForm;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.ui.ExtendedModelMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeePageControllerTest {
    @Test
    void listPageShowsSearchResultsAndDepartments() {
        EmployeeService service = mock(EmployeeService.class);
        DepartmentRepository departments = mock(DepartmentRepository.class);
        Department department = new Department();
        department.setId(1L);
        department.setName("IT");
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Nguyen Van An");
        employee.setDepartment(department);
        when(service.search("An", 1L)).thenReturn(List.of(employee));
        when(departments.findAll()).thenReturn(List.of(department));

        Authentication admin = new UsernamePasswordAuthenticationToken(
                "admin", "none", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        ExtendedModelMap model = new ExtendedModelMap();
        EmployeePageController controller = new EmployeePageController(service, departments);

        assertEquals("employees", controller.list("An", 1L, admin, model));
        assertEquals(List.of(employee), model.get("employees"));
        assertEquals(List.of(department), model.get("departments"));
        assertEquals(true, model.get("isAdmin"));
        verify(service).search("An", 1L);
    }

    @Test
    void addPageProvidesEmptyFormAndDepartmentOptions() {
        EmployeeService service = mock(EmployeeService.class);
        DepartmentRepository departments = mock(DepartmentRepository.class);
        when(departments.findAll()).thenReturn(List.of());
        EmployeePageController controller = new EmployeePageController(service, departments);
        ExtendedModelMap model = new ExtendedModelMap();

        assertEquals("employee-form", controller.addForm(model));
        assertInstanceOf(EmployeeForm.class, model.get("form"));
        assertEquals(List.of(), model.get("departments"));
    }
}
