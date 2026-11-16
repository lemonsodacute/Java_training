package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Department;
import com.example.employeemanagement.repository.DepartmentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentRepository repository;
    public DepartmentController(DepartmentRepository repository) { this.repository = repository; }
    @GetMapping
    public List<Department> list() { return repository.findAll(); }
    @PostMapping
    public ResponseEntity<Department> add(@Valid @RequestBody Department input) {
        input.setId(null);
        input.setName(input.getName().trim());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(input));
    }
}
