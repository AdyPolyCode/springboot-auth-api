package com.poly.schoolDataManager.services;

import com.poly.schoolDataManager.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private TeacherRepository repository;

    @Autowired
    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }
}
