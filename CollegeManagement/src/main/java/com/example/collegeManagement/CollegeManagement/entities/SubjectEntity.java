package com.example.collegeManagement.CollegeManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subjects")
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(
            name = "teaching_professor_id",
            referencedColumnName = "id"
    )
    private ProfessorEntity professor;

    @ManyToMany(mappedBy = "subjects")
    private List<StudentEntity> students;

}
