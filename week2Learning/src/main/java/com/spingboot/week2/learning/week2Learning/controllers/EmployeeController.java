package com.spingboot.week2.learning.week2Learning.controllers;

import com.spingboot.week2.learning.week2Learning.dto.EmployeesDTO;
import com.spingboot.week2.learning.week2Learning.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeesDTO> getEmployeeById(@PathVariable(name = "employeeId") long id){
        Optional<EmployeesDTO> employeesDTO = employeeService.getEmployeeById(id);
        return employeesDTO.map(employeesDTO1 -> ResponseEntity.ok(employeesDTO1)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeesDTO>> getAllEmployee(@RequestParam(required = false, name = "inputAge") Integer age){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeesDTO> checkingPostRequest(@RequestBody EmployeesDTO inputEmployee){
        return new ResponseEntity<>(employeeService.createEmployee(inputEmployee), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeesDTO> updateEmployeeById(@RequestBody EmployeesDTO employeesDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeesDTO,employeeId));
    }
    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeesDTO> patchEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
        EmployeesDTO employeesDTO =  employeeService.patchEmployeeById(updates, employeeId);
        if(employeesDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeesDTO);
    }
}
