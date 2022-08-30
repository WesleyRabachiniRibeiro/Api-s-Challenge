package com.savelife.project.dto.ambulance;

import com.savelife.project.entities.Phone;

import java.util.List;

public class RegistryAmbulance {

    private String licensePlate;


    private List<Phone> phones;

    public RegistryAmbulance(String licensePlate, List<Phone> phones) {
        this.licensePlate = licensePlate;
        this.phones = phones;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
