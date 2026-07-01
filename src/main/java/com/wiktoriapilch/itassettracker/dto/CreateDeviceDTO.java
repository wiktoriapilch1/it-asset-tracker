package com.wiktoriapilch.itassettracker.dto;

import com.wiktoriapilch.itassettracker.constants.ErrorMessages;
import com.wiktoriapilch.itassettracker.models.devices.DeviceStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateDeviceDTO(

        @NotBlank(message = ErrorMessages.DEVICE_NAME_REQUIRED)
        String name,

        @NotBlank(message = ErrorMessages.DEVICE_SERIAL_NUMBER_REQUIRED)
        @Pattern(regexp = "^SN-.*", message = ErrorMessages.SERIAL_NUMBER_PREFIX_STARTS_WITH_SN)
        String serialNumber) {

}