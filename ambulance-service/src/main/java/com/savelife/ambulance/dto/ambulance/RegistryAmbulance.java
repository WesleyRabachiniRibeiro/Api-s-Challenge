package com.savelife.ambulance.dto.ambulance;

import com.savelife.ambulance.entities.Phone;

import java.util.List;

public class RegistryAmbulance {

    private String licensePlate;

    private List<Phone> phone;

    private List<Long> hospital;

    public RegistryAmbulance() {
    }

    public RegistryAmbulance(String licensePlate, List<Phone> phone, List<Long> hospital) {
        this.licensePlate = licensePlate;
        this.phone = phone;
        this.hospital = hospital;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public List<Long> getHospital() {
        return hospital;
    }

    public void setHospital(List<Long> hospital) {
        this.hospital = hospital;
    }
}
