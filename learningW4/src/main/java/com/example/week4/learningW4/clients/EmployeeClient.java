package com.example.week4.learningW4.clients;

import com.example.week4.learningW4.dto.EmployeesDTO;

import java.util.List;

public interface EmployeeClient {

    List<EmployeesDTO> getAllEmployees();
    EmployeesDTO getEmployeeById(Long id);
    EmployeesDTO createNewEmployee(EmployeesDTO employee);
}
