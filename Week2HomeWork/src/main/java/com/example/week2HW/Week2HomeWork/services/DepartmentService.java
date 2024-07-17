package com.example.week2HW.Week2HomeWork.services;

import com.example.week2HW.Week2HomeWork.dto.DepartmentDTO;
import com.example.week2HW.Week2HomeWork.entities.DepartmentEntity;
import com.example.week2HW.Week2HomeWork.exceptions.ResourceNotFoundException;
import com.example.week2HW.Week2HomeWork.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment) {
        DepartmentEntity toSaveEntity = modelMapper.map(inputDepartment,DepartmentEntity.class);
        DepartmentEntity savedEntity = departmentRepository.save(toSaveEntity);
        return modelMapper.map(savedEntity,DepartmentDTO.class);
    }


    public List<DepartmentDTO> getAllDepartment() {
        List<DepartmentEntity> allDepartments = departmentRepository.findAll();
        return allDepartments
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO updateDepartmentById(DepartmentDTO inputDepartment, Long departmentId) {
        isExistDepartment(departmentId);
        DepartmentEntity entityToSave = modelMapper.map(inputDepartment,DepartmentEntity.class);
        entityToSave.setId(departmentId);
        DepartmentEntity savedEntity = departmentRepository.save(entityToSave);
        return modelMapper.map(savedEntity, DepartmentDTO.class);
    }

    private void isExistDepartment(Long id){
        boolean exists = departmentRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Department not found with id: " + id);
    }

    public boolean deleteDepartmentById(Long id) {
        isExistDepartment(id);
        departmentRepository.deleteById(id);
        return true;
    }

    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository.findById(id).map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class));
    }
}
