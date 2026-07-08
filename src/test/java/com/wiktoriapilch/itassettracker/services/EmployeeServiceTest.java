package com.wiktoriapilch.itassettracker.services;

import com.wiktoriapilch.itassettracker.dto.device.DeviceResponseDTO;
import com.wiktoriapilch.itassettracker.models.devices.Device;
import com.wiktoriapilch.itassettracker.models.devices.DeviceStatus;
import com.wiktoriapilch.itassettracker.models.employees.Employee;
import com.wiktoriapilch.itassettracker.repository.EmployeeRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

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

    @Test
    void shouldThrowExceptionWhenDeviceBelongsToSomeoneElse() {
        EmployeeRepository mockEmployeeRepository = Mockito.mock(EmployeeRepository.class);
        DeviceService mockDeviceService = Mockito.mock(DeviceService.class);
        EmployeeService employeeService = new EmployeeService(mockEmployeeRepository, mockDeviceService);

        Employee employeeX = new Employee("John", "Smith", "john.smith@google.com");
        ReflectionTestUtils.setField(employeeX, "id", 1L);

        Employee employeeY = new Employee("Eva", "Baker", "eva.baker@google.com");
        ReflectionTestUtils.setField(employeeY, "id", 2L);
        Device device = new Device("Dell", "SN-1122", DeviceStatus.ASSIGNED);
        device.setEmployee(employeeY);

        Mockito.when(mockEmployeeRepository.findById(1L)).thenReturn(Optional.of(employeeX));
        Mockito.when(mockDeviceService.getDeviceBySerialNumber("SN-1122")).thenReturn(device);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            employeeService.unassignDeviceFromEmployee(1L, "SN-1122");
        });
    }
}
