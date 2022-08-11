package com.savelife.ambulance.dto.ambulance;

import com.savelife.ambulance.dto.phone.PhoneDTO;

import java.util.List;

public class SearchAmbulance {
    private String licensePlate;

    private List<PhoneDTO> phone;

    private List<Long> hospital;

    public SearchAmbulance() {
    }


    public SearchAmbulance(String licensePlate, List<PhoneDTO> phone) {
        this.licensePlate = licensePlate;
        this.phone = phone;
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<PhoneDTO> getPhone() {
        return phone;
    }

    public void setPhone(List<PhoneDTO> phone) {
        this.phone = phone;
    }

    public List<Long> getHospital() {
        return hospital;
    }

    public void setHospital(List<Long> hospital) {
        this.hospital = hospital;
    }
}
