package com.wiktoriapilch.itassettracker.devices;

public class Device {
    private Long id;
    private String name;
    private String serialNumber;
    private DeviceStatus status;

    public Device(String name, String serialNumber, DeviceStatus deviceStatus) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.status = deviceStatus;
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
