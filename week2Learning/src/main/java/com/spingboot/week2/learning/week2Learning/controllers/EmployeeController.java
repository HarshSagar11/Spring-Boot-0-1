package com.spingboot.week2.learning.week2Learning.controllers;

import com.spingboot.week2.learning.week2Learning.dto.EmployeesDTO;
import com.spingboot.week2.learning.week2Learning.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    //use getmapping annotation use to define path
//    @GetMapping(path = "/employee")
//    public String getEmployee(){
//        return "here is employee";
//    }

    @GetMapping(path = "/{employeeId}")
    public EmployeesDTO getEmployeeById(@PathVariable(name = "employeeId") long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeesDTO> getAllEmployee(@RequestParam(required = false, name = "inputAge") Integer age){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeesDTO checkingPostRequest(@RequestBody EmployeesDTO inputEmployee){
        return employeeService.createEmployee(inputEmployee);
    }
}
