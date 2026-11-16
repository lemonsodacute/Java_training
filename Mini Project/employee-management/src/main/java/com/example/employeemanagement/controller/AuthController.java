package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.RegisterForm;
import com.example.employeemanagement.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final AccountService accounts;
    public AuthController(AccountService accounts) { this.accounts = accounts; }
    @GetMapping("/")
    public String home() { return "redirect:/employees"; }
    @GetMapping("/login")
    public String login() { return "login"; }
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("form", new RegisterForm());
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("form") RegisterForm form,
                           BindingResult result, Model model) {
        if (result.hasErrors()) return "register";
        if (!accounts.register(form)) {
            model.addAttribute("error", "Ten dang nhap da ton tai");
            return "register";
        }
        return "redirect:/login?registered";
    }
}
