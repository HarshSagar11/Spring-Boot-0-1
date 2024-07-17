package com.example.week2HW.Week2HomeWork.repositories;

import com.example.week2HW.Week2HomeWork.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
