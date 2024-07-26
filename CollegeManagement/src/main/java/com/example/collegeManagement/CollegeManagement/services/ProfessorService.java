package com.example.collegeManagement.CollegeManagement.services;

import com.example.collegeManagement.CollegeManagement.entities.ProfessorEntity;
import com.example.collegeManagement.CollegeManagement.entities.SubjectEntity;
import com.example.collegeManagement.CollegeManagement.repositories.ProfessorRepository;
import com.example.collegeManagement.CollegeManagement.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;

    public ProfessorService(ProfessorRepository professorRepository, SubjectRepository subjectRepository) {
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<ProfessorEntity> getAllProfessor() {
        return professorRepository.findAll();
    }

    public ProfessorEntity createNewProfessor(ProfessorEntity professor) {
        return professorRepository.save(professor);
    }

    public ProfessorEntity getProfessorById(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    public ProfessorEntity assignSubjectToProfessor(Long professorId, Long subjectId) {
        Optional<ProfessorEntity> professor = professorRepository.findById(professorId);
        Optional<SubjectEntity> subject = subjectRepository.findById(subjectId);

        return professor.flatMap(professor1 ->
                subject.map(subjectEntity -> {
                    subjectEntity.setProfessor(professor1);
                    subjectRepository.save(subjectEntity);
                    professor1.getSubjects().add(subjectEntity);
                    return professor1;
                })).orElse(null);
    }
}
