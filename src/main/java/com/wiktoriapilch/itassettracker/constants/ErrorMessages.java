package com.wiktoriapilch.itassettracker.constants;

public class ErrorMessages {
    // DEVICES
    public static final String DEVICE_WITH_ID_NOT_FOUND = "Device with ID %s not found";
    public static final String DEVICE_NAME_REQUIRED = "Device name is required";
    public static final String DEVICE_SERIAL_NUMBER_REQUIRED = "Device serial number is required";
    public static final String DEVICE_SERIAL_NUMBER_PREFIX_STARTS_WITH_SN = "Serial number must start with 'SN-' prefix";
    public static final String DEVICE_WITH_SERIAL_NUMBER_NOT_FOUND = "Device with Serial Number %s not found";

    // EMPLOYEES
    public static final String EMPLOYEE_FIRST_NAME_CANNOT_BE_EMPTY = "First name cannot be empty";
    public static final String EMPLOYEE_LAST_NAME_CANNOT_BE_EMPTY = "Last name cannot be empty";
    public static final String EMPLOYEE_EMAIL_FORMAT_INVALID = "Invalid email format";
    public static final String EMPLOYEE_EMAIL_CANNOT_BE_EMPTY = "Invalid email format";
    public static final String EMPLOYEE_WITH_ID_NOT_FOUND = "Employee with ID %s not found";
    public static final String DEVICE_ALREADY_ASSIGNED_OR_IN_REPAIR = "Device is already assigned or in repair";

    // OTHER
    public static final String BAD_REQUEST = "Bad body request";
    public static final String USERNAME_NOT_FOUND = "Username not found";
    public static final String USERNAME_ALREADY_EXISTS = "Username is already taken";
}
