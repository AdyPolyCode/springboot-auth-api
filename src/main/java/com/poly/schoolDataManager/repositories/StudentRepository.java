package com.poly.schoolDataManager.repositories;

import com.poly.schoolDataManager.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
