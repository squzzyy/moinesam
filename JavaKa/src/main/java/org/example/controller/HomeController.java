package org.example.controller;

import org.example.bd.Request;
import org.example.bd.User;
import org.example.dto.LoginRequest;
import org.example.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private RequestService requestService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping("/create-request")
    public String showCreateRequestForm(Model model) {
        model.addAttribute("request", new Request());
        return "create_request";
    }

    @GetMapping("/admin")
    public String showAdminPanel(Model model) {
        model.addAttribute("requests", requestService.getAllRequests());
        return "admin_panel";
    }
}
