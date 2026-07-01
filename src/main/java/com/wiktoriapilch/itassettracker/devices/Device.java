package com.wiktoriapilch.itassettracker.devices;

import com.wiktoriapilch.itassettracker.constants.ErrorMessages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = ErrorMessages.DEVICE_NAME_REQUIRED)
    private String name;

    @Column(name = "serial_number")
    @NotBlank(message = ErrorMessages.DEVICE_SERIAL_NUMBER_REQUIRED)
    @Pattern(regexp = "^SN-.*", message = "Serial number must start with 'SN-' prefix")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    protected Device() {}

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
