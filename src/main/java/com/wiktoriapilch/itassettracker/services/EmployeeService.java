package com.wiktoriapilch.itassettracker.services;

import com.wiktoriapilch.itassettracker.dto.employee.CreateEmployeeDTO;
import com.wiktoriapilch.itassettracker.models.employees.Employee;
import com.wiktoriapilch.itassettracker.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(CreateEmployeeDTO newEmployee) {
        Employee employee = new Employee(newEmployee.firstName(), newEmployee.lastName(), newEmployee.email());
        return this.employeeRepository.save(employee);
    }
}
