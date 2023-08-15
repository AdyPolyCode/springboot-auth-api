package com.poly.schoolDataManager.controllers;

import com.poly.schoolDataManager.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private SubjectService service;

    @Autowired
    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
