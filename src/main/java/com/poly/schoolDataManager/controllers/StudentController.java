package com.poly.schoolDataManager.controllers;

import com.poly.schoolDataManager.payload.response.EntityResPayload;
import com.poly.schoolDataManager.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityResPayload> getById(@PathVariable Long id) {
        EntityResPayload payload = service.getById(id);

        return ResponseEntity.ok(payload);
    }

    @GetMapping
    public ResponseEntity<List<EntityResPayload>> getAll() {
        List<EntityResPayload> payload = service.getAll();

        return ResponseEntity.ok(payload);
    }
}
