package com.savelife.ambulance.dto.ambulance;

import com.savelife.ambulance.dto.hospital.HospitalDTO;
import com.savelife.ambulance.dto.phone.PhoneDTO;

import java.util.List;

public class SearchAmbulanceAndHospital {

    private String licensePlate;

    private List<PhoneDTO> phone;

    private List<HospitalDTO> hospital;

    public SearchAmbulanceAndHospital() {
    }

    public SearchAmbulanceAndHospital(String licensePlate, List<PhoneDTO> phone, List<HospitalDTO> hospital) {
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

    public List<PhoneDTO> getPhone() {
        return phone;
    }

    public void setPhone(List<PhoneDTO> phone) {
        this.phone = phone;
    }

    public List<HospitalDTO> getHospital() {
        return hospital;
    }

    public void setHospital(List<HospitalDTO> hospital) {
        this.hospital = hospital;
    }
}
