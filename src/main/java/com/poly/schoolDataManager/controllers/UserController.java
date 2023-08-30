package com.poly.schoolDataManager.controllers;

import com.poly.schoolDataManager.payload.request.UserCreatePayload;
import com.poly.schoolDataManager.payload.request.UserUpdatePayload;
import com.poly.schoolDataManager.payload.response.UserPayload;
import com.poly.schoolDataManager.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserPayload> create(@Valid @RequestBody UserCreatePayload payload) {
        UserPayload user = service.create(payload);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UserPayload>> getAll() {
        List<UserPayload> payload = service.getAll();

        return ResponseEntity.ok(payload);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPayload> getById(@PathVariable Long id) {
        UserPayload payload = service.getById(id);

        return ResponseEntity.ok(payload);
    }

    @GetMapping("/name")
    public ResponseEntity<UserPayload> getByUsername(@RequestParam(name = "username") String username) {
        UserPayload payload = service.getByUsername(username);

        return ResponseEntity.ok(payload);
    }

    @GetMapping("/mail")
    public ResponseEntity<UserPayload> getByEmail(@RequestParam(name = "email") String email) {
        UserPayload payload = service.getByEmail(email);

        return ResponseEntity.ok(payload);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPayload> updateById(@Valid @RequestBody UserUpdatePayload payload, @PathVariable Long id) {
        UserPayload p = service.updateById(id, payload);

        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
