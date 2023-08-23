package com.poly.schoolDataManager.controllers;

import com.poly.schoolDataManager.payload.response.EntityPayload;
import com.poly.schoolDataManager.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private TeacherService service;

    @Autowired
    public TeacherController(TeacherService service) {
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

    @GetMapping("/firstN")
    public ResponseEntity<List<EntityPayload>> getAllByFirstName(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit
    ) {
        List<EntityPayload> payload = service.getAllByFirstName(firstName, limit);

        return ResponseEntity.ok(payload);
    }

    @GetMapping("/lastN")
    public ResponseEntity<List<EntityPayload>> getAllByLastName(
            @RequestParam(name = "lastname") String lastName,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit
    ) {
        List<EntityPayload> payload = service.getAllByLastName(lastName, limit);

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
