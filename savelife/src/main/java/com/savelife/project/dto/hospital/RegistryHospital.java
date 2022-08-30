package com.savelife.project.dto.hospital;


import com.savelife.project.entities.Address;
import com.savelife.project.entities.Phone;

import java.util.List;

public class RegistryHospital {

    private String name;

    private Address address;

    private List<Phone> phones;

    private List<Long> ambulances;

    public RegistryHospital() {
    }

    public RegistryHospital(String name, Address address, List<Phone> phones, List<Long> ambulances) {
        this.name = name;
        this.address = address;
        this.phones = phones;
        this.ambulances = ambulances;
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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Long> getAmbulances() {
        return ambulances;
    }

    public void setAmbulances(List<Long> ambulances) {
        this.ambulances = ambulances;
    }
}
