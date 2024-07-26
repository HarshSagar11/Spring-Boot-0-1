package com.example.collegeManagement.CollegeManagement.repositories;

import com.example.collegeManagement.CollegeManagement.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity,Long> {
}
