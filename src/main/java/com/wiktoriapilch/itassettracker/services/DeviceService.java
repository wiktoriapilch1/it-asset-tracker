package com.wiktoriapilch.itassettracker.services;

import com.wiktoriapilch.itassettracker.constants.ErrorMessages;
import com.wiktoriapilch.itassettracker.dto.CreateDeviceDTO;
import com.wiktoriapilch.itassettracker.dto.UpdateDeviceDTO;
import com.wiktoriapilch.itassettracker.exception.ResourceNotFoundException;
import com.wiktoriapilch.itassettracker.models.devices.Device;
import com.wiktoriapilch.itassettracker.repository.DeviceRepository;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DeviceService {
    private  final DeviceRepository deviceRepository;
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device addDevice(CreateDeviceDTO dtoDevice) {
        Device newDevice = new Device(dtoDevice.name(), dtoDevice.serialNumber());
        return deviceRepository.save(newDevice);
    }

    public List<Device> getAllDevices() {
        return this.deviceRepository.findAll();
    }

    public Device getDeviceBySerialNumber(String serialNumber) {
        return this.deviceRepository.findBySerialNumber(serialNumber).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.DEVICE_WITH_SERIAL_NUMBER_NOT_FOUND, serialNumber))
        );
    }

    public void deleteDevice(Long id) {
        this.deviceRepository.deleteById(id);
    }

    public Device updateDevice(Long id, UpdateDeviceDTO updates) {
        Device device_db = deviceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.DEVICE_WITH_ID_NOT_FOUND, id))
        );

        if(StringUtils.hasText(updates.name())) {
            device_db.setName(updates.name());
        }
        if(StringUtils.hasText(updates.serialNumber())) {
            device_db.setSerialNumber(updates.serialNumber());
        }
        if (updates.status() != null) {
            device_db.setStatus(updates.status());
        }

        deviceRepository.save(device_db);
        return device_db;
    }
}
