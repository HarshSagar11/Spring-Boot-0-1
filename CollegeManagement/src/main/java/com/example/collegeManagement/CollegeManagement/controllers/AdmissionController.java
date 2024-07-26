package com.example.collegeManagement.CollegeManagement.controllers;

import com.example.collegeManagement.CollegeManagement.entities.AdmissionRecordEntity;
import com.example.collegeManagement.CollegeManagement.services.AdmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admission-record")
public class AdmissionController {

    private final AdmissionService admissionService;

    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @PostMapping
    public AdmissionRecordEntity createAdmissionRecord(@RequestBody AdmissionRecordEntity admissionRecord){
        return admissionService.createAdmissionRecord(admissionRecord);
    }
    @GetMapping
    public List<AdmissionRecordEntity> getAllAdmissionRecords(){
        return admissionService.getAllAdmissionRecord();
    }
    @GetMapping(path = "/{admission_id}/student/{student_id}")
    public AdmissionRecordEntity addStudentToAdmissionRecord(@PathVariable Long admission_id,
                                                             @PathVariable Long student_id){
        return admissionService.setStudentToAdmissionRecord(admission_id,student_id);
    }
}
