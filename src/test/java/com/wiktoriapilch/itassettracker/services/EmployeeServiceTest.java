package com.wiktoriapilch.itassettracker.services;

import com.wiktoriapilch.itassettracker.dto.device.DeviceResponseDTO;
import com.wiktoriapilch.itassettracker.models.devices.Device;
import com.wiktoriapilch.itassettracker.models.devices.DeviceStatus;
import com.wiktoriapilch.itassettracker.models.employees.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class EmployeeServiceTest {
    @Test
    void shouldMapToDeviceResponseDTO() {
        Employee employee = new Employee("John", "Smith", "john.smith@google.com");
        ReflectionTestUtils.setField(employee, "id", 1L);
        Device device = new Device("pc", "SN-5678", DeviceStatus.ASSIGNED);
        device.setEmployee(employee);
        EmployeeService employeeService = new EmployeeService(null, null);
        DeviceResponseDTO result = employeeService.mapToDeviceResponseDTO(device);
        Assertions.assertEquals(1L, result.employeeId());
    }
}
