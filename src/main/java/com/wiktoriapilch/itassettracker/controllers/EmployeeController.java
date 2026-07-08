package com.wiktoriapilch.itassettracker.controllers;

import com.wiktoriapilch.itassettracker.constants.ErrorMessages;
import com.wiktoriapilch.itassettracker.dto.device.DeviceResponseDTO;
import com.wiktoriapilch.itassettracker.dto.employee.CreateEmployeeDTO;
import com.wiktoriapilch.itassettracker.models.devices.Device;
import com.wiktoriapilch.itassettracker.models.employees.Employee;
import com.wiktoriapilch.itassettracker.services.EmployeeService;

import jakarta.validation.Valid;

import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@Valid @RequestBody CreateEmployeeDTO newEmployee) {
        return this.employeeService.addEmployee(newEmployee);
    }

    @PatchMapping("/{employeeId}/devices/{serialNumber}")
    public List<DeviceResponseDTO> assignDeviceToEmployee(
            @PathVariable Long employeeId,
            @Pattern(regexp = "^SN-.*", message = ErrorMessages.DEVICE_SERIAL_NUMBER_PREFIX_STARTS_WITH_SN)
            @PathVariable String serialNumber) {
        return this.employeeService.assignDeviceToEmployee(employeeId, serialNumber);
    }

    @DeleteMapping("/{employeeId}/devices/{serialNumber}")
    public List<DeviceResponseDTO> unassignDeviceFromEmployee (
            @PathVariable Long employeeId,
            @Pattern(regexp = "^SN-.*", message = ErrorMessages.DEVICE_SERIAL_NUMBER_PREFIX_STARTS_WITH_SN)
            @PathVariable String serialNumber) {
        return this.employeeService.unassignDeviceFromEmployee(employeeId, serialNumber);
    }
}
