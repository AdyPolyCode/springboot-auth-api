package com.poly.schoolDataManager.services;

import com.poly.schoolDataManager.entities.Teacher;
import com.poly.schoolDataManager.exceptions.NotFoundEntityException;
import com.poly.schoolDataManager.payload.response.EntityResPayload;
import com.poly.schoolDataManager.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private TeacherRepository repository;

    @Autowired
    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public EntityResPayload getById(Long id) {
        Optional<Teacher> teacher = repository.findById(id);

        return teacher.map(data -> {
            EntityResPayload payload = new EntityResPayload();

            payload.setFirstName(data.getFirstName());
            payload.setLastName(data.getLastName());
            payload.setAge(data.getAge());
            payload.setPhoneNumber(data.getPhoneNumber());

            return payload;
        }).orElseThrow(() -> new NotFoundEntityException("Teacher with id '" + id + "' was not found!"));
    }

    public List<EntityResPayload> getAll() {
        List<Teacher> student = repository.findAll();
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
}
