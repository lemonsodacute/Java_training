package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.RegisterForm;
import com.example.employeemanagement.model.AppUser;
import com.example.employeemanagement.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    public AccountService(UserRepository users, PasswordEncoder encoder) {
        this.users = users; this.encoder = encoder;
    }
    @Transactional
    public boolean register(RegisterForm form) {
        String username = form.getUsername().trim();
        if (users.existsByUsernameIgnoreCase(username)) return false;
        users.save(new AppUser(username, encoder.encode(form.getPassword()), "ROLE_USER"));
        return true;
    }
}
