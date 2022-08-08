package com.savelife.hospital.dto.hospital;


import com.savelife.hospital.entities.Address;
import com.savelife.hospital.entities.Phone;

import java.util.List;

public class RegistryHospital {

    private String name;

    private Address address;

    private List<Phone> phone;

    public RegistryHospital() {
    }

    public RegistryHospital(String name, Address address, List<Phone> phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }
}
