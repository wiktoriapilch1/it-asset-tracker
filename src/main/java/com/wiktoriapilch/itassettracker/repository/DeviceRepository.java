package com.wiktoriapilch.itassettracker.repository;

import com.wiktoriapilch.itassettracker.models.devices.Device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    public Optional<Device> findBySerialNumber(String serialNumber);
}
