package com.spingboot.week2.learning.week2Learning.repositories;

//import com.spingboot.week2.learning.week2Learning.dto.EmployeesDTO;
import com.spingboot.week2.learning.week2Learning.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
