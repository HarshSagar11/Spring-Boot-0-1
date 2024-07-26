package com.example.collegeManagement.CollegeManagement.controllers;

import com.example.collegeManagement.CollegeManagement.entities.StudentEntity;
import com.example.collegeManagement.CollegeManagement.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentEntity> getAllStudents(){
        return studentService.getAllStudent();
    }

    @GetMapping(path = "/{student_id}")
    public StudentEntity getStudentById(@PathVariable Long student_id){
        return studentService.getStudentById(student_id);
    }

    @PostMapping
    public StudentEntity createNewStudent(@RequestBody StudentEntity studentObj){
        return studentService.createNewStudent(studentObj);
    }
    @GetMapping(path = "/{student_id}/subject/{subject_id}")
    public StudentEntity assignSubjectToStudent(@PathVariable Long student_id,
                                                @PathVariable Long subject_id){
        return studentService.assignSubjectToStudent(student_id,subject_id);
    }
}
