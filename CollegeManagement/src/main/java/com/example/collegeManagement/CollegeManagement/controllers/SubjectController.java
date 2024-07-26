package com.example.collegeManagement.CollegeManagement.controllers;

import com.example.collegeManagement.CollegeManagement.entities.SubjectEntity;
import com.example.collegeManagement.CollegeManagement.services.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<SubjectEntity> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    @PostMapping
    public SubjectEntity createNewSubject(@RequestBody SubjectEntity subjectEntity){
        return subjectService.createNewSubject(subjectEntity);
    }
    @GetMapping(path = "/{subject_id}")
    public SubjectEntity getAllSubjects(@PathVariable Long subject_id){
        return subjectService.getSubjectById(subject_id);
    }

}
