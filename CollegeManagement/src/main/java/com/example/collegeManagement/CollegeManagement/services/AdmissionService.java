package com.example.collegeManagement.CollegeManagement.services;

import com.example.collegeManagement.CollegeManagement.entities.AdmissionRecordEntity;
import com.example.collegeManagement.CollegeManagement.entities.StudentEntity;
import com.example.collegeManagement.CollegeManagement.repositories.AdmissionRecordRepository;
import com.example.collegeManagement.CollegeManagement.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdmissionService {
    private final AdmissionRecordRepository admissionRecordRepository;
    private final StudentRepository studentRepository;

    public AdmissionService(AdmissionRecordRepository admissionRecordRepository, StudentRepository studentRepository) {
        this.admissionRecordRepository = admissionRecordRepository;
        this.studentRepository = studentRepository;
    }

    public AdmissionRecordEntity createAdmissionRecord(AdmissionRecordEntity admissionRecord) {
        return admissionRecordRepository.save(admissionRecord);
    }

    public AdmissionRecordEntity setStudentToAdmissionRecord(Long admissionId, Long studentId) {
        Optional<AdmissionRecordEntity> admissionRecord = admissionRecordRepository.findById(admissionId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        return admissionRecord.flatMap(admissionRecord1 ->
                studentEntity.map(studentEntity1 -> {
                    admissionRecord1.setStudent(studentEntity1);
                    return admissionRecordRepository.save(admissionRecord1);
                })
        ).orElse(null);
    }

    public List<AdmissionRecordEntity> getAllAdmissionRecord() {
        return admissionRecordRepository.findAll();
    }
}
