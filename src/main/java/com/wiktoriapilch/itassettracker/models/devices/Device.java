package com.wiktoriapilch.itassettracker.models.devices;

import com.wiktoriapilch.itassettracker.models.employees.Employee;
import jakarta.persistence.*;

@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "serial_number")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    protected Device() {}

    public Device(String name, String serialNumber, DeviceStatus deviceStatus) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.status = deviceStatus;
    }

    public Device(String name, String serialNumber) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.status = DeviceStatus.AVAILABLE;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }
}
