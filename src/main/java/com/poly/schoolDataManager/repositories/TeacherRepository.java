package com.poly.schoolDataManager.repositories;

import com.poly.schoolDataManager.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
