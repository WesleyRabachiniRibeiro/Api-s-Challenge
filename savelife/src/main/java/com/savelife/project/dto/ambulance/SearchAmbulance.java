package com.savelife.project.dto.ambulance;

import com.savelife.project.dto.phone.SearchPhone;

import java.util.List;

public class SearchAmbulance {
    private String licensePlate;

    private boolean status;

    private List<SearchPhone> phones;

    public SearchAmbulance(String licensePlate, boolean status, List<SearchPhone> phones) {
        this.licensePlate = licensePlate;
        this.status = status;
        this.phones = phones;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<SearchPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<SearchPhone> phones) {
        this.phones = phones;
    }
}
