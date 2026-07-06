package com.wiktoriapilch.itassettracker.repository;

import com.wiktoriapilch.itassettracker.models.employees.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
