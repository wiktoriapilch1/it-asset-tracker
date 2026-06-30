package com.wiktoriapilch.itassettracker.controllers;

import com.wiktoriapilch.itassettracker.devices.Device;
import com.wiktoriapilch.itassettracker.devices.DeviceStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeviceController {

    @GetMapping("/api/devices")
    public List<Device> getAllDevices() {
        return List.of(
                new Device("Dell Latitude", "SN123", DeviceStatus.AVAILABLE),
                new Device("MacBook Pro", "SN456", DeviceStatus.ASSIGNED)
        );
    }

    @PostMapping("/api/devices")
    public String addDevice(@RequestBody Device device) {
        return "created" + device.getName();
    }
}
