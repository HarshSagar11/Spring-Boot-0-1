package com.example.week2HW.Week2HomeWork.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 20, message = "Title Length must be of size 3 to 20")
    private String title;

    @NotNull(message = "isActive is required list")
    private boolean isActive;
    @PastOrPresent(message = "Date can be less than or equal to today")
    private LocalDate createdAt;
}
