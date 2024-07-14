package com.spingboot.week2.learning.week2Learning.services;

import com.spingboot.week2.learning.week2Learning.dto.EmployeesDTO;
import com.spingboot.week2.learning.week2Learning.entities.EmployeeEntity;
import com.spingboot.week2.learning.week2Learning.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeesDTO getEmployeeById(Long id){
       EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
       return modelMapper.map(employeeEntity,EmployeesDTO.class);
    }

    public List<EmployeesDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeesDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeesDTO createEmployee(EmployeesDTO employeeInput){
        EmployeeEntity toSaveEntity = modelMapper.map(employeeInput,EmployeeEntity.class);
        EmployeeEntity toSaveEmployee =  employeeRepository.save(toSaveEntity);
        return modelMapper.map(toSaveEmployee,EmployeesDTO.class);
    }

}
