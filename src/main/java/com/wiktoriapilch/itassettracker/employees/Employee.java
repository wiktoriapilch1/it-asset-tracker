package com.wiktoriapilch.itassettracker.employees;

import com.wiktoriapilch.itassettracker.devices.Device;
import com.wiktoriapilch.itassettracker.devices.DeviceStatus;

import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "first name cannot be empty")
    private String firstName;

    @NotBlank(message = "last name cannot be empty")
    private String lastName;

    @Email(message = "invalid email format")
    @NotBlank(message = "email cannot be empty")
    private String email;

    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    @Size(max = 5, message = "Employee cannot have more than 5 devices")
    private List<Device> assignedDevices = new ArrayList<>();

    protected Employee() {}

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void assignDevice(Device device) {
        device.setStatus(DeviceStatus.ASSIGNED);
        assignedDevices.add(device);
    }

    public List<Device> getAssignedDevices() {
        return assignedDevices;
    }
}
