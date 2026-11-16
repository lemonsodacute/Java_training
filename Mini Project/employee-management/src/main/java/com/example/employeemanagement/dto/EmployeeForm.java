package com.example.employeemanagement.dto;

import jakarta.validation.constraints.*;

public class EmployeeForm {
    @NotBlank(message = "Vui long nhap ten")
    @Size(max = 100, message = "Ten toi da 100 ky tu")
    private String name;
    @NotBlank(message = "Vui long nhap email")
    @Email(message = "Email khong hop le")
    private String email;
    @NotNull(message = "Vui long chon phong ban")
    private Long departmentId;

    public EmployeeForm() {}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
}
