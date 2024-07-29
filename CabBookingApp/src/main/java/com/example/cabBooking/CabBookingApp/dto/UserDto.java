package com.example.cabBooking.CabBookingApp.dto;

import com.example.cabBooking.CabBookingApp.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private Set<Role> role;
}
