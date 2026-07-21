package com.wiktoriapilch.itassettracker.dto.auth;

import com.wiktoriapilch.itassettracker.models.employees.RoleType;

public record RegisterRequestDTO(
        String username,
        String password,
        RoleType role
) {
    public RegisterRequestDTO {
        if(role == null) {
            role = RoleType.EMPLOYEE;
        }
    }
}
