package com.wiktoriapilch.itassettracker.dto.auth;

import com.wiktoriapilch.itassettracker.models.employees.RoleType;

public record RegisterResponseDTO(
   String username,
   RoleType role
) {}
