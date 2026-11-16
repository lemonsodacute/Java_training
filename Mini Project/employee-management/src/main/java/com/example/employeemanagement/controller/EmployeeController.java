package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeForm;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service = service; }

    @GetMapping
    public List<Employee> list(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Long departmentId) {
        return service.search(name, departmentId);
    }
    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) { return service.get(id); }

    @PostMapping
    public ResponseEntity<Employee> add(@Valid @RequestBody EmployeeForm input) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(input));
    }
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody EmployeeForm input) {
        return service.update(id, input);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
