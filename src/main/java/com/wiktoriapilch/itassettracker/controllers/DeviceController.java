package com.wiktoriapilch.itassettracker.controllers;

import com.wiktoriapilch.itassettracker.dto.CreateDeviceDTO;
import com.wiktoriapilch.itassettracker.dto.UpdateDeviceDTO;
import com.wiktoriapilch.itassettracker.models.devices.Device;

import com.wiktoriapilch.itassettracker.services.DeviceService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device addDevice(@Valid @RequestBody CreateDeviceDTO dtoDevice) {
        return deviceService.addDevice(dtoDevice);
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
    }

    @PatchMapping("/{id}")
    public Device updateDevice(@PathVariable Long id, @Valid @RequestBody UpdateDeviceDTO updates) {
        return deviceService.updateDevice(id, updates);
    }
}
