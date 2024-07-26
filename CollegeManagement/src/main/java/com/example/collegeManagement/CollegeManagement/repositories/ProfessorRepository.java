package com.example.collegeManagement.CollegeManagement.repositories;

import com.example.collegeManagement.CollegeManagement.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity,Long> {
}
