package com.example.employeemanagement.controller;

import com.example.employeemanagement.service.UtilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/demo/employees")
public class DemoEmployeeController {
    private final List<DemoEmployee> employees = new ArrayList<>();
    private long nextId = 1;
    private final UtilityService utilityService;

    public DemoEmployeeController(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    @GetMapping
    public List<DemoEmployee> getAll() {
        return employees;
    }

    @PostMapping
    public ResponseEntity<DemoEmployee> add(@RequestBody DemoEmployee input) {
        DemoEmployee employee = new DemoEmployee(
                nextId++,
                utilityService.formatName(input.name()),
                input.email()
        );
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    public record DemoEmployee(Long id, String name, String email) {}
}
