package com.spingboot.week2.learning.week2Learning.services;

import com.spingboot.week2.learning.week2Learning.dto.EmployeesDTO;
import com.spingboot.week2.learning.week2Learning.entities.EmployeeEntity;
import com.spingboot.week2.learning.week2Learning.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeesDTO> getEmployeeById(Long id){
       return employeeRepository.findById(id).map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeesDTO.class));
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

    public EmployeesDTO updateEmployeeById(EmployeesDTO employeesDTO, Long employeeId) {
        EmployeeEntity employeeToUpdate = modelMapper.map(employeesDTO,EmployeeEntity.class);
        employeeToUpdate.setId(employeeId);
        EmployeeEntity savedEmployee = employeeRepository.save(employeeToUpdate);
        return modelMapper.map(savedEmployee,EmployeesDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists = isexistByEmployeeId(employeeId);
        if(!exists) return false;
        employeeRepository.deleteById(employeeId);
        return  true;
    }

    public EmployeesDTO patchEmployeeById(Map<String, Object> updates, Long employeeId) {
        boolean exist = isexistByEmployeeId(employeeId);
        if(!exist) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value) -> {
            Field fieldToUpdate = ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate,employeeEntity,value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeesDTO.class);
    }

    private boolean isexistByEmployeeId(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }
}
