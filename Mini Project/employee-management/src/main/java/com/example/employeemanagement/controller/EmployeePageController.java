package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeForm;
import com.example.employeemanagement.exception.BadRequestException;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeePageController {
    private final EmployeeService employees;
    private final DepartmentRepository departments;

    public EmployeePageController(EmployeeService employees, DepartmentRepository departments) {
        this.employees = employees; this.departments = departments;
    }
    private boolean admin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
    @GetMapping("/employees")
    public String list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Long departmentId,
                       Authentication authentication, Model model) {
        model.addAttribute("employees", employees.search(name, departmentId));
        model.addAttribute("departments", departments.findAll());
        model.addAttribute("name", name);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("isAdmin", admin(authentication));
        model.addAttribute("username", authentication.getName());
        return "employees";
    }
    private void setupEmployeeForm(Model model, Long id) {
        model.addAttribute("departments", departments.findAll());
        model.addAttribute("formId", id);
    }
    @GetMapping("/employees/new")
    public String addForm(Model model) {
        model.addAttribute("form", new EmployeeForm());
        setupEmployeeForm(model, null);
        return "employee-form";
    }
    @GetMapping("/employees/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Employee e = employees.get(id);
        EmployeeForm form = new EmployeeForm();
        form.setName(e.getName()); form.setEmail(e.getEmail());
        form.setDepartmentId(e.getDepartment().getId());
        model.addAttribute("form", form);
        setupEmployeeForm(model, id);
        return "employee-form";
    }
    @PostMapping("/employees/save")
    public String save(@RequestParam(required = false) Long id,
                       @Valid @ModelAttribute("form") EmployeeForm form,
                       BindingResult result, Model model) {
        if (!result.hasErrors()) {
            try {
                if (id == null) employees.create(form);
                else employees.update(id, form);
                return "redirect:/employees";
            } catch (BadRequestException | ResourceNotFoundException ex) {
                model.addAttribute("error", ex.getMessage());
            }
        }
        setupEmployeeForm(model, id);
        return "employee-form";
    }
    @PostMapping("/employees/delete/{id}")
    public String delete(@PathVariable Long id) {
        employees.delete(id);
        return "redirect:/employees";
    }
    @GetMapping("/departments")
    public String departments(Authentication authentication, Model model) {
        model.addAttribute("departments", departments.findAll());
        model.addAttribute("isAdmin", admin(authentication));
        return "departments";
    }
    @PostMapping("/departments")
    public String createDepartment(@RequestParam String name, Model model, Authentication authentication) {
        if (name == null || name.isBlank()) {
            model.addAttribute("error", "Vui long nhap ten phong ban");
        } else {
            try {
                Department dep = new Department();
                dep.setName(name.trim());
                departments.saveAndFlush(dep);
                return "redirect:/departments";
            } catch (DataIntegrityViolationException ex) {
                model.addAttribute("error", "Ten phong ban da ton tai");
            }
        }
        model.addAttribute("departments", departments.findAll());
        model.addAttribute("isAdmin", admin(authentication));
        return "departments";
    }
}
