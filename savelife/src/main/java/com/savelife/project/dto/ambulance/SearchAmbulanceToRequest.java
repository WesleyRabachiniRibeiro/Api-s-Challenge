package com.savelife.project.dto.ambulance;

import com.savelife.project.dto.address.phone.SearchPhone;

import java.util.List;

public class SearchAmbulanceToRequest {
    private String licensePlate;

    private List<SearchPhone> phones;

    public SearchAmbulanceToRequest(String licensePlate, List<SearchPhone> phones) {
        this.licensePlate = licensePlate;
        this.phones = phones;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<SearchPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<SearchPhone> phones) {
        this.phones = phones;
    }
}
