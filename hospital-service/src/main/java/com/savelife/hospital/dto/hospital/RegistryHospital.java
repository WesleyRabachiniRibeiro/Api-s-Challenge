package com.savelife.hospital.dto.hospital;


import com.savelife.hospital.entities.Address;

public class RegistryHospital {

    private String name;

    private Address address;

    public RegistryHospital(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
