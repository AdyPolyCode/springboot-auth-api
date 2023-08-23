package com.poly.schoolDataManager.services;

import com.poly.schoolDataManager.entities.Student;
import com.poly.schoolDataManager.entities.Teacher;
import com.poly.schoolDataManager.exceptions.NotFoundEntityException;
import com.poly.schoolDataManager.payload.PayloadMapper;
import com.poly.schoolDataManager.payload.response.EntityPayload;
import com.poly.schoolDataManager.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public EntityPayload getById(Long id) {
        Optional<Student> student = repository.findById(id);

        if(student.isEmpty()) {
            throw new NotFoundEntityException("Student with id '" + id + "' was not found!");
        }

        EntityPayload payload = new EntityPayload();
        new PayloadMapper<Student, EntityPayload>(student.get(), payload).mapTo(payload);

        return payload;
    }

    public List<EntityPayload> getAllByFirstName(String firstName, Integer limit) {
        List<Student> students = repository.findAllByFirstName(firstName, limit);
        List<EntityPayload> payload = new ArrayList<>();

        students.forEach(student -> {
            EntityPayload e = new EntityPayload();
            new PayloadMapper<Student, EntityPayload>(student, e).mapTo(e);
            payload.add(e);
        });

        return payload;
    }

    public List<EntityPayload> getAllByLastName(String lastName, Integer limit) {
        List<Student> students = repository.findAllByLastName(lastName, limit);
        List<EntityPayload> payload = new ArrayList<>();

        students.forEach(student -> {
            EntityPayload e = new EntityPayload();
            new PayloadMapper<Student, EntityPayload>(student, e).mapTo(e);
            payload.add(e);
        });

        return payload;
    }

    public List<EntityPayload> getAll() {
        List<Student> student = repository.findAll();
        List<EntityPayload> payload = new ArrayList<>();

        student.forEach(data -> {
            EntityPayload ep = new EntityPayload();
            PayloadMapper<Student, EntityPayload> mapper = new PayloadMapper<>(data, ep);

            mapper.mapTo(ep);
            payload.add(ep);
        });

        return payload;
    }

    public EntityPayload create(EntityPayload payload) {
        Student student = new Student();
        PayloadMapper<EntityPayload, Student> mapper = new PayloadMapper<>(payload, student);
        Date currentDate = new java.util.Date();

        mapper.mapTo(payload);
        student.setCreatedAt(currentDate);
        student.setUpdatedAt(currentDate);
        repository.saveAndFlush(student);

        return payload;
    }

    public EntityPayload updateById(Long id, EntityPayload payload) {
        Optional<Student> student = repository.findById(id);

        if(student.isEmpty()) {
            throw new NotFoundEntityException("Student with id '" + id + "' was not found!");
        }

        EntityPayload ep = new EntityPayload();
        Student s = student.get();

        new PayloadMapper<EntityPayload, Student>(payload, s).mapTo(payload);
        repository.save(s);
        new PayloadMapper<Student, EntityPayload>(s, ep).mapTo(ep);

        return ep;
    }

    public void deleteById(Long id) {
        Optional<Student> student = repository.findById(id);

        if(student.isEmpty()) {
            throw new NotFoundEntityException("Student with id '" + id + "' was not found!");
        }

        repository.delete(student.get());
    }
}
