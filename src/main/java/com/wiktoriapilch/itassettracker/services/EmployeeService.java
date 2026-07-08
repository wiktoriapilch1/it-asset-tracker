package com.wiktoriapilch.itassettracker.services;

import com.wiktoriapilch.itassettracker.constants.ErrorMessages;
import com.wiktoriapilch.itassettracker.dto.device.DeviceResponseDTO;
import com.wiktoriapilch.itassettracker.dto.employee.CreateEmployeeDTO;
import com.wiktoriapilch.itassettracker.exception.ResourceNotFoundException;
import com.wiktoriapilch.itassettracker.models.devices.Device;
import com.wiktoriapilch.itassettracker.models.devices.DeviceStatus;
import com.wiktoriapilch.itassettracker.models.employees.Employee;
import com.wiktoriapilch.itassettracker.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DeviceService deviceService;

    public EmployeeService(EmployeeRepository employeeRepository, DeviceService deviceService) {
        this.employeeRepository = employeeRepository;
        this.deviceService = deviceService;
    }

    public Employee addEmployee(CreateEmployeeDTO newEmployee) {
        Employee employee = new Employee(newEmployee.firstName(), newEmployee.lastName(), newEmployee.email());
        return this.employeeRepository.save(employee);
    }

    public List<DeviceResponseDTO> assignDeviceToEmployee(Long employeeId, String serialNumber) {
        Employee employee_db = this.employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.EMPLOYEE_WITH_ID_NOT_FOUND, employeeId))
        );
        Device device = this.deviceService.getDeviceBySerialNumber(serialNumber);
        if (device.getStatus() == DeviceStatus.ASSIGNED || device.getStatus() == DeviceStatus.IN_REPAIR) {
            throw new IllegalStateException(String.format(ErrorMessages.DEVICE_ALREADY_ASSIGNED_OR_IN_REPAIR));
        }
        employee_db.assignDevice(device);
        this.employeeRepository.save(employee_db);
        return employee_db.getAssignedDevices().stream().map(this::mapToDeviceResponseDTO).toList();
    }

    public DeviceResponseDTO mapToDeviceResponseDTO(Device device) {
        return new DeviceResponseDTO(device.getName(), device.getSerialNumber(), device.getStatus(), device.getEmployee() != null ? device.getEmployee().getId() : null);
    }
}
