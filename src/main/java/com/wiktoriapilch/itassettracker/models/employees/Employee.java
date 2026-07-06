package com.wiktoriapilch.itassettracker.models.employees;

import com.wiktoriapilch.itassettracker.models.devices.Device;
import com.wiktoriapilch.itassettracker.models.devices.DeviceStatus;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(mappedBy = "employee")
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
        device.setEmployee(this);
    }

    public List<Device> getAssignedDevices() {
        return assignedDevices;
    }

    public Long getId() {
        return id;
    }
}
