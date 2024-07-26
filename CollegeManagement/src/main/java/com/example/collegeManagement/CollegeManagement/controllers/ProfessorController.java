package com.example.collegeManagement.CollegeManagement.controllers;

import com.example.collegeManagement.CollegeManagement.entities.ProfessorEntity;
import com.example.collegeManagement.CollegeManagement.entities.StudentEntity;
import com.example.collegeManagement.CollegeManagement.services.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public List<ProfessorEntity> getAllProfessor(){
        return professorService.getAllProfessor();
    }

    @GetMapping(path = "/{professor_id}/students")
    public List<StudentEntity> getStudentsOfProfessor(@PathVariable Long professor_id){
        return professorService.getStudentsOfProfessor(professor_id);
    }

    @PostMapping
    public ProfessorEntity createNewProfessor(@RequestBody ProfessorEntity professor){
        return professorService.createNewProfessor(professor);
    }

    @GetMapping(path = "/{id}")
    public ProfessorEntity getProfessorById(@PathVariable Long id){
        return professorService.getProfessorById(id);
    }

    @GetMapping(path = "/{professor_id}/subject/{subject_id}")
    public ProfessorEntity assignSubjectToProfessor(@PathVariable Long professor_id,
                                                    @PathVariable Long subject_id){
        return professorService.assignSubjectToProfessor(professor_id,subject_id);
    }
}
