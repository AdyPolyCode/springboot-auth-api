package com.poly.schoolDataManager.controllers;

import com.poly.schoolDataManager.entities.User;
import com.poly.schoolDataManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login() { return null; }

    @PostMapping("/register")
    public ResponseEntity<User> register() { return null; }
}
