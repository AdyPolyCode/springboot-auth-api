package com.poly.schoolDataManager.repositories;

import com.poly.schoolDataManager.entities.Teacher;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeacherRepository extends BaseEntityRepository<Teacher, Long> {
    @Query(value = "SELECT t from Teacher t WHERE t.teacherUser =: teacherUser")
    Optional<Teacher> findByTeacherUser(Long teacherUser);
}
