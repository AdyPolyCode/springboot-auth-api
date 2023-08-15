package com.poly.schoolDataManager.repositories;

import com.poly.schoolDataManager.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
