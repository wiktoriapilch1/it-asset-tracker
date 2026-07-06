package com.wiktoriapilch.itassettracker.dto.device;

import com.wiktoriapilch.itassettracker.models.devices.DeviceStatus;

public record DeviceResponseDTO(
        String name,
        String serialNumber,
        DeviceStatus status,
        Long employeeId) {}
