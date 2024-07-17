package com.example.week2HW.Week2HomeWork.controller;

import com.example.week2HW.Week2HomeWork.dto.DepartmentDTO;
import com.example.week2HW.Week2HomeWork.entities.DepartmentEntity;
import com.example.week2HW.Week2HomeWork.exceptions.ResourceNotFoundException;
import com.example.week2HW.Week2HomeWork.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }
    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "departmentId") Long id){
        Optional<DepartmentDTO> departmentDetails = departmentService.getDepartmentById(id);
        return departmentDetails
                .map(departmentDTO -> ResponseEntity.ok(departmentDTO))
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO inputDepartment) {
        DepartmentDTO departmentDTO = departmentService.createNewDepartment(inputDepartment);
        return new ResponseEntity<>(departmentDTO, HttpStatus.CREATED);
    }
    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody @Valid DepartmentDTO inputDepartment, @PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.updateDepartmentById(inputDepartment,departmentId));
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable(name = "departmentId") Long id){
        boolean getDeleted = departmentService.deleteDepartmentById(id);
        if (getDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
}
