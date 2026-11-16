package com.example.employeemanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;
    @NotBlank @Email @Size(max = 150)
    @Column(nullable = false, unique = true, length = 150)
    private String email;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public Employee() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
