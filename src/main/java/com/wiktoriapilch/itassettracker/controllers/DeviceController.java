package com.wiktoriapilch.itassettracker.controllers;

import com.wiktoriapilch.itassettracker.constants.ErrorMessages;
import com.wiktoriapilch.itassettracker.dto.CreateDeviceDTO;
import com.wiktoriapilch.itassettracker.models.devices.Device;
import com.wiktoriapilch.itassettracker.exception.ResourceNotFoundException;
import com.wiktoriapilch.itassettracker.repository.DeviceRepository;

import jakarta.validation.Valid;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    private  final DeviceRepository deviceRepository;

    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @PostMapping
    public Device addDevice(@Valid @RequestBody CreateDeviceDTO dtoDevice) {
        Device newDevice = new Device(dtoDevice.name(), dtoDevice.serialNumber());
        return deviceRepository.save(newDevice);
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id) {
        deviceRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Device updateDevice(@PathVariable Long id, @Valid @RequestBody Device updates) {
        Device device_db = deviceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.DEVICE_NOT_FOUND, id))
        );

        if(StringUtils.hasText(updates.getName())) {
            device_db.setName(updates.getName());
        }
        if(StringUtils.hasText(updates.getSerialNumber())) {
            device_db.setSerialNumber(updates.getSerialNumber());
        }
        if (updates.getStatus() != null) {
            device_db.setStatus(updates.getStatus());
        }

        deviceRepository.save(device_db);
        return device_db;
    }
}
