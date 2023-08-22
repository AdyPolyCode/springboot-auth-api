package com.poly.schoolDataManager.controllers;

import com.poly.schoolDataManager.payload.response.EntityPayload;
import com.poly.schoolDataManager.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<EntityPayload> getById(@PathVariable Long id) {
        EntityPayload payload = service.getById(id);

        return ResponseEntity.ok(payload);
    }

    @GetMapping
    public ResponseEntity<List<EntityPayload>> getAll() {
        List<EntityPayload> payload = service.getAll();

        return ResponseEntity.ok(payload);
    }

    @PostMapping
    public ResponseEntity<EntityPayload> create(@RequestBody EntityPayload body) {
        EntityPayload payload = service.create(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(payload);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityPayload> updateById(@RequestBody EntityPayload body, @PathVariable Long id) {
        EntityPayload payload = service.updateById(id, body);

        return ResponseEntity.ok(payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        service.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
