package com.example.employeemanagement;

import com.example.employeemanagement.service.EmployeeCodeGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeCodeGeneratorTest {
    @Test
    void shouldGenerateEmployeeCode() {
        EmployeeCodeGenerator generator = new EmployeeCodeGenerator("EMP-");
        assertEquals("EMP-0012", generator.generate(12L));
    }
}
