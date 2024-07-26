package com.example.collegeManagement.CollegeManagement.repositories;

import com.example.collegeManagement.CollegeManagement.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
}
