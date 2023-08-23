package com.poly.schoolDataManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.poly.schoolDataManager.entities.BaseEntity;

import java.util.List;

@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity, ID extends Number> extends JpaRepository<T, ID> {
    @Query(value = "SELECT e FROM #{#entityName} e WHERE e.firstName = :firstName ORDER BY e.firstName ASC LIMIT :max")
    List<T> findAllByFirstName(String firstName, int max);

    @Query(value = "SELECT e FROM #{#entityName} e WHERE e.lastName = :lastName ORDER BY e.lastName ASC LIMIT :max")
    List<T> findAllByLastName(String lastName, int max);
}
