package com.example.employeemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> notFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequest(BadRequestException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getField() + ": " + e.getDefaultMessage())
            .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(Map.of("error", message));
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> conflict(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(Map.of("error", "Du lieu bi trung hoac vi pham rang buoc database"));
    }
}
