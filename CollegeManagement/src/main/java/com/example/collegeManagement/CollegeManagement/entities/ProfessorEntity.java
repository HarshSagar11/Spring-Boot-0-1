package com.example.collegeManagement.CollegeManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "professors")
@NoArgsConstructor
@AllArgsConstructor

public class ProfessorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "professor")
    private List<SubjectEntity> subjects;

    @ManyToMany(mappedBy = "professors")
    private List<StudentEntity> students;
}
