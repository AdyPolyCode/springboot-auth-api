package com.poly.schoolDataManager.repositories;

import com.poly.schoolDataManager.entities.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends BaseEntityRepository<Student, Long> {
    @Query(value = "SELECT s from Student s WHERE s.studentUser =: studentUser")
    Optional<Student> findByStudentUser(Long studentUser);
}
