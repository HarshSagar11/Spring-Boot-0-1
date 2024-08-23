package com.week5Learning.Security.W5Learning.dto;

import com.week5Learning.Security.W5Learning.entities.enums.Permissions;
import com.week5Learning.Security.W5Learning.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignupDto {

    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
    private Set<Permissions> permissions;
}
