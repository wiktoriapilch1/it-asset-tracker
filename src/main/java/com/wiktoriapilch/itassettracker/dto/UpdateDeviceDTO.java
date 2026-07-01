package com.wiktoriapilch.itassettracker.dto;

import com.wiktoriapilch.itassettracker.constants.ErrorMessages;
import com.wiktoriapilch.itassettracker.models.devices.DeviceStatus;

import jakarta.validation.constraints.Pattern;

public record UpdateDeviceDTO(
        String name,

        @Pattern(regexp = "^SN-.*", message = ErrorMessages.SERIAL_NUMBER_PREFIX_STARTS_WITH_SN)
        String serialNumber,

        DeviceStatus status) {}