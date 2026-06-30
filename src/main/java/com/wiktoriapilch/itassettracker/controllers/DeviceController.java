package com.wiktoriapilch.itassettracker.controllers;

import com.wiktoriapilch.itassettracker.devices.Device;
import com.wiktoriapilch.itassettracker.repository.DeviceRepository;

import jakarta.validation.Valid;

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
    public Device addDevice(@Valid @RequestBody Device device) {
        return deviceRepository.save(device);
    }
}
