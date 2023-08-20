package com.poly.schoolDataManager.services;

import com.poly.schoolDataManager.entities.Student;
import com.poly.schoolDataManager.exceptions.NotFoundEntityException;
import com.poly.schoolDataManager.payload.response.EntityResPayload;
import com.poly.schoolDataManager.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public EntityResPayload getById(Long id) {
        Optional<Student> student = repository.findById(id);

        return student.map(data -> {
            EntityResPayload payload = new EntityResPayload();

            payload.setFirstName(data.getFirstName());
            payload.setLastName(data.getLastName());
            payload.setAge(data.getAge());
            payload.setPhoneNumber(data.getPhoneNumber());

            return payload;
        }).orElseThrow(() -> new NotFoundEntityException("Student with id '" + id + "' was not found!"));
    }

    public List<EntityResPayload> getAll() {
        List<Student> student = repository.findAll();
        List<EntityResPayload> payload = new ArrayList<>();

        student.forEach(data -> {
            EntityResPayload ep = new EntityResPayload();

            ep.setFirstName(data.getFirstName());
            ep.setLastName(data.getLastName());
            ep.setAge(data.getAge());
            ep.setPhoneNumber(data.getPhoneNumber());

            payload.add(ep);
        });

        return payload;
    }

    public EntityResPayload updateById(Long id, EntityResPayload payload) {
        Optional<Student> student = repository.findById(id);

        return student.map(data -> {
            data.setFirstName(payload.getFirstName());
            data.setLastName(payload.getLastName());
            data.setAge(payload.getAge());
            data.setPhoneNumber(payload.getPhoneNumber());
            data.setUpdatedAt(Date.valueOf(LocalDate.now()));

            repository.saveAndFlush(data);
            // TODO create a dto and dao mapper
            return new EntityResPayload();
        }).orElseThrow(() -> new NotFoundEntityException("Student with id '" + id + "' was not found!"));
    }
}
