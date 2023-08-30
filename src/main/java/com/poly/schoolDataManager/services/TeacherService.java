package com.poly.schoolDataManager.services;

import com.poly.schoolDataManager.entities.Teacher;
import com.poly.schoolDataManager.exceptions.NotFoundEntityException;
import com.poly.schoolDataManager.payload.PayloadMapper;
import com.poly.schoolDataManager.payload.response.EntityPayload;
import com.poly.schoolDataManager.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private TeacherRepository repository;

    @Autowired
    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public EntityPayload getById(Long id) {
        Optional<Teacher> teacher = repository.findById(id);

        if(teacher.isEmpty()) {
            throw new NotFoundEntityException("Teacher with id '" + id + "' was not found!");
        }

        EntityPayload payload = new EntityPayload();
        new PayloadMapper<Teacher, EntityPayload>(teacher.get(), payload).mapWithParamsFrom(payload);

        return payload;
    }

    public List<EntityPayload> getAllByFirstName(String firstName, Integer limit) {
        List<Teacher> teachers = repository.findAllByFirstName(firstName, limit);
        List<EntityPayload> payload = new ArrayList<>();

        teachers.forEach(teacher -> {
            EntityPayload e = new EntityPayload();
            new PayloadMapper<Teacher, EntityPayload>(teacher, e).mapWithParamsFrom(e);
            payload.add(e);
        });

        return payload;
    }

    public List<EntityPayload> getAllByLastName(String lastName, Integer limit) {
        List<Teacher> teachers = repository.findAllByLastName(lastName, limit);
        List<EntityPayload> payload = new ArrayList<>();

        teachers.forEach(teacher -> {
            EntityPayload e = new EntityPayload();
            new PayloadMapper<Teacher, EntityPayload>(teacher, e).mapWithParamsFrom(e);
            payload.add(e);
        });

        return payload;
    }

    public List<EntityPayload> getAll() {
        List<Teacher> teacher = repository.findAll();
        List<EntityPayload> payload = new ArrayList<>();

        teacher.forEach(data -> {
            EntityPayload ep = new EntityPayload();
            PayloadMapper<Teacher, EntityPayload> mapper = new PayloadMapper<>(data, ep);

            mapper.mapWithParamsFrom(ep);
            payload.add(ep);
        });

        return payload;
    }

    public EntityPayload create(EntityPayload payload) {
        Teacher teacher = new Teacher();
        PayloadMapper<EntityPayload, Teacher> mapper = new PayloadMapper<>(payload, teacher);
        Date currentDate = new Date();

        mapper.mapWithParamsFrom(payload);
        teacher.setCreatedAt(currentDate);
        teacher.setUpdatedAt(currentDate);
        repository.saveAndFlush(teacher);

        return payload;
    }

    public EntityPayload updateById(Long id, EntityPayload payload) {
        Optional<Teacher> teacher = repository.findById(id);

        if(teacher.isEmpty()) {
            throw new NotFoundEntityException("Teacher with id '" + id + "' was not found!");
        }

        EntityPayload ep = new EntityPayload();
        Teacher t = teacher.get();

        new PayloadMapper<EntityPayload, Teacher>(payload, t).mapWithParamsFrom(payload);
        repository.save(t);
        new PayloadMapper<Teacher, EntityPayload>(t, ep).mapWithParamsFrom(ep);

        return ep;
    }

    public void deleteById(Long id) {
        Optional<Teacher> teacher = repository.findById(id);

        if(teacher.isEmpty()) {
            throw new NotFoundEntityException("Teacher with id '" + id + "' was not found!");
        }

        repository.delete(teacher.get());
    }
}
