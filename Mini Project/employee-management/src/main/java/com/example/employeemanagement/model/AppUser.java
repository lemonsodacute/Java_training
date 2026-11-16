package com.example.employeemanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 20)
    private String role;

    public AppUser() {}
    public AppUser(String username, String password, String role) {
        this.username = username; this.password = password; this.role = role;
    }
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}
