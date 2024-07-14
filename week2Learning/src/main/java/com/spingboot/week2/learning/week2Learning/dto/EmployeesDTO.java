package com.spingboot.week2.learning.week2Learning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesDTO {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isActive;

}
