package com.example.collegeManagement.CollegeManagement.repositories;

import com.example.collegeManagement.CollegeManagement.entities.ProfessorEntity;
import com.example.collegeManagement.CollegeManagement.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
    List<StudentEntity> findByProfessors(Optional<ProfessorEntity> professor);
}
