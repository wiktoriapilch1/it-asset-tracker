package com.wiktoriapilch.itassettracker.dto.employee;

import com.wiktoriapilch.itassettracker.constants.ErrorMessages;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateEmployeeDTO(
        @NotBlank(message = ErrorMessages.EMPLOYEE_FIRST_NAME_CANNOT_BE_EMPTY)
        String firstName,

        @NotBlank(message = ErrorMessages.EMPLOYEE_LAST_NAME_CANNOT_BE_EMPTY)
        String lastName,

        @Email(message = ErrorMessages.EMPLOYEE_EMAIL_FORMAT_INVALID)
        @NotBlank(message = ErrorMessages.EMPLOYEE_EMAIL_CANNOT_BE_EMPTY)
        String email) {}