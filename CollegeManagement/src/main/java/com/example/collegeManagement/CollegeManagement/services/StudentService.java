package com.example.collegeManagement.CollegeManagement.services;

import com.example.collegeManagement.CollegeManagement.entities.AdmissionRecordEntity;
import com.example.collegeManagement.CollegeManagement.entities.ProfessorEntity;
import com.example.collegeManagement.CollegeManagement.entities.StudentEntity;
import com.example.collegeManagement.CollegeManagement.entities.SubjectEntity;
import com.example.collegeManagement.CollegeManagement.repositories.StudentRepository;
import com.example.collegeManagement.CollegeManagement.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<StudentEntity> getAllStudent() {
        return studentRepository.findAll();
    }

    public StudentEntity getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public StudentEntity createNewStudent(StudentEntity studentObj) {
        return studentRepository.save(studentObj);
    }

    public StudentEntity assignSubjectToStudent(Long studentId, Long subjectId) {
        Optional<StudentEntity> student = studentRepository.findById(studentId);
        Optional<SubjectEntity> subject = subjectRepository.findById(subjectId);

        return student.flatMap(studentEntity ->
                subject.map(subjectEntity -> {
                    studentEntity.getSubjects().add(subjectEntity);
                    ProfessorEntity professor = subjectEntity.getProfessor();
                    List<ProfessorEntity> professors = studentEntity.getProfessors();
                    if(!professors.contains(professor)){
                        studentEntity.getProfessors().add(professor);
                    }
                    studentRepository.save(studentEntity);
                    return studentEntity;
        })).orElse(null);
    }
}
