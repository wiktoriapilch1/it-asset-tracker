package com.wiktoriapilch.itassettracker;

import com.wiktoriapilch.itassettracker.devices.Device;
import com.wiktoriapilch.itassettracker.devices.DeviceStatus;
import com.wiktoriapilch.itassettracker.employees.Employee;

public class Main {
    public static void main(String[] args) {
        System.out.println("IT Asset Tracker ### Starting");
        Employee employee = new Employee("John", "Pat", "john.pat@google.com");
        Device laptop = new Device("MacBook Pro", "SN-987114", DeviceStatus.AVAILABLE);
    }
}
