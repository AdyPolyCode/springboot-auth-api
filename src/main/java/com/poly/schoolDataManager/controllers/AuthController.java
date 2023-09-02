package com.poly.schoolDataManager.controllers;

import com.poly.schoolDataManager.payload.request.UserCreatePayload;
import com.poly.schoolDataManager.payload.request.UserLoginPayload;
import com.poly.schoolDataManager.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService service;

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginPayload payload) {
        String token = service.login(payload.getUsername(), payload.getPassword());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserCreatePayload payload) {
        String token = service.register(payload);

        return ResponseEntity.ok(token);
    }
}
