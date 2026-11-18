package com.example.employeemanagement;

import com.example.employeemanagement.controller.EmployeeController;
import com.example.employeemanagement.exception.ApiExceptionHandler;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeeApiValidationTest {
    private MockMvc mvc;
    private EmployeeService service;

    @BeforeEach
    void setup() {
        service = mock(EmployeeService.class);
        mvc = MockMvcBuilders.standaloneSetup(new EmployeeController(service))
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    void invalidEmployeeReturns400AndFieldErrors() throws Exception {
        mvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\" \",\"email\":\"wrong-email\",\"departmentId\":null}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error", containsString("name")))
            .andExpect(jsonPath("$.error", containsString("email")))
            .andExpect(jsonPath("$.error", containsString("departmentId")));
    }

    @Test
    void unknownEmployeeReturns404() throws Exception {
        when(service.get(999L)).thenThrow(new ResourceNotFoundException("Khong tim thay nhan vien ID 999"));
        mvc.perform(get("/api/employees/999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error", containsString("999")));
    }

    @Test
    void malformedJsonReturns400() throws Exception {
        mvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{not valid json}"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error", containsString("JSON")));
    }

    @Test
    void wrongDepartmentParameterReturns400() throws Exception {
        mvc.perform(get("/api/employees?departmentId=abc"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error", containsString("departmentId")));
    }
}
